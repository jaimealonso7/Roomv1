package com.example.roomv1.views

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.roomv1.models.Facturas
import com.example.roomv1.viewmodels.FacturasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacturasListView(navController: NavController, viewModel: FacturasViewModel) {
    // 🔹 Observamos los cambios en la lista de facturas
    val facturas = viewModel.state.facturasList // Accede directamente al estado

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Pantalla de Inicio", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Blue
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = "FacturasAddView") },
                containerColor = Color.Blue,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { paddingValues ->
        ContentView(
            paddingValues = paddingValues,
            navController = navController,
            facturas = facturas,
            viewModel = viewModel
        )
    }
}

@Composable
fun ContentView(
    paddingValues: PaddingValues,
    navController: NavController,
    facturas: List<Facturas>,
    viewModel: FacturasViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(facturas) { facturas ->
                FacturaItem(facturas, navController, viewModel)
            }
        }
    }
}

@Composable
fun FacturaItem(factura: Facturas, navController: NavController, viewModel: FacturasViewModel) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(2.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(text = "Número de Factura: ${factura.numeroFactura}")
            Text(text = "Fecha de Emisión: ${factura.fechaEmision}")
            Text(text = "Empresa: ${factura.empresa}")
            Text(text = "NIF: ${factura.nif}")
            Text(text = "Dirección: ${factura.direccion}")
            Text(text = "Base Imponible: ${"%.2f".format(factura.baseImponible)}")
            Text(text = "IVA: ${"%.2f".format(factura.iva)}%")
            Text(
                text = "Total: ${"%.2f".format(factura.total)}€",
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )

            Row {
                IconButton(onClick = {
                    println("Navegando a FacturasUpdateView con ID: ${factura.id}") // 🔹 Verifica el ID enviado
                    Log.d("FacturasApp", "🔹 Navegando a FacturasUpdateView con ID: ${factura.id}")
                    // Asegúrate de que se pasa correctamente el id de la factura
                    navController.navigate("FacturasUpdateView/${factura.id}")
                }) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar Factura")
                }

                IconButton(onClick = { viewModel.borrarFactura(factura) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar")
                }
            }
        }
    }
}

