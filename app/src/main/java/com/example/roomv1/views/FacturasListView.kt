package com.example.roomv1.views

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomv1.viewmodels.FacturasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacturasListView(navController: NavController, viewModel: FacturasViewModel, numeroFactura: String = "", fechaEmision: String = "",
                     empresa: String = "", nif: String = "", direccion: String = "", baseImponible: Double = 0.0, iva: Double = 0.0, total: Double = 0.0) {

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
                onClick = { navController.navigate(route= "FacturasAddView") },
                containerColor = Color.Blue,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) {
        ContentView(
            it, navController, viewModel,
            numeroFactura, fechaEmision, empresa, nif, direccion, baseImponible, iva, total
        )
    }
}

@Composable

fun ContentView(it: PaddingValues, navController: NavController, viewModel: FacturasViewModel,  numeroFactura: String, fechaEmision: String, empresa: String, nif: String, direccion: String, baseImponible: Double, iva: Double, total: Double) {
    val state = viewModel.state
    var numeroFactura by remember { mutableStateOf(numeroFactura) }
    var fechaEmision by remember { mutableStateOf(fechaEmision) }
    var empresa by remember { mutableStateOf(empresa) }
    var nif by remember { mutableStateOf(nif) }
    var direccion by remember { mutableStateOf(direccion) }
    var baseImponible by remember { mutableStateOf(baseImponible) } // Asigna el valor pasado al composable
    var iva by remember { mutableStateOf(iva) }
    var total by remember { mutableStateOf(total) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(it)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize() // Permite que la lista ocupe todo el espacio disponible
                .weight(1f)
        ) {
            items(state.facturasList) {
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
                            .padding(16.dp) // Espaciado interno dentro del borde
                    ) {

                        Text(text = "Número de Factura: ${it.numeroFactura}")
                        Text(text = "Fecha de Emisión: ${it.fechaEmision}")
                        Text(text = "Empresa: ${it.empresa}")
                        Text(text = "NIF: ${it.nif}")
                        Text(text = "Dirección: ${it.direccion}")
                        Text(text = "Base Imponible: ${"%.2f".format(it.baseImponible)}")
                        Text(text = "IVA: ${"%.2f".format(it.iva)}%")
                        Text(
                            text = "Total: ${"%.2f".format(it.total)}€",
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue
                        )
                        Row() {
                            IconButton(
                                onClick = {
                                    navController.navigate(
                                        "FacturasUpdateView/${it.numeroFactura}/${it.fechaEmision}/${it.empresa}/${it.nif}/${it.direccion}/${it.baseImponible}/${it.iva}/${it.total}"
                                    )
                                }
                                //onClick = { navController.navigate("editar/${it.id}/${it.numeroFactura}/${it.fechaEmision}/${it.empresa}/${it.nif}/${it.direccion}/${it.baseImponible}/${it.iva}/${it.total}") }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Editar"
                                )
                            }

                            IconButton(
                                onClick = { viewModel.borrarFactura(it) }
                            ) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar")
                            }
                        }
                    }
                }

            }
        }
    }
}