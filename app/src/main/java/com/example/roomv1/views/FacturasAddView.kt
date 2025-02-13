package com.example.roomv1.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomv1.models.Facturas
import com.example.roomv1.viewmodels.FacturasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacturasAddView(navController: NavController, viewModel: FacturasViewModel, numeroFactura: String = "", fechaEmision: String = "",
                    empresa: String = "", nif: String = "", direccion: String = "", baseImponible: Double = 0.0, iva: Double = 0.0, total: Double = 0.0) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Agregar Factura", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Red
                ),
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Regresar", tint = Color.White)
                    }
                }
            )
        }
    ) {
        ContentAddView(
            it, navController, viewModel,
            numeroFactura, fechaEmision, empresa, nif, direccion, baseImponible, iva, total
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentAddView(it: PaddingValues, navController: NavController, viewModel: FacturasViewModel, numeroFactura: String, fechaEmision: String, empresa: String, nif: String, direccion: String, baseImponible: Double, iva: Double, total: Double) {
    var numeroFactura by remember { mutableStateOf(numeroFactura) }
    var fechaEmision by remember { mutableStateOf(fechaEmision) }
    var empresa by remember { mutableStateOf(empresa) }
    var nif by remember { mutableStateOf(nif) }
    var direccion by remember { mutableStateOf(direccion) }

    var baseImponibleText by remember { mutableStateOf(baseImponible.toString()) }
    var ivaText by remember { mutableStateOf(iva.toString()) }
    var totalText by remember { mutableStateOf(total.toString()) }

    var baseImponible by remember { mutableStateOf(baseImponible) }
    var iva by remember { mutableStateOf(iva) }
    var total by remember { mutableStateOf(total) }


    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = numeroFactura ?: "",
            onValueChange = { numeroFactura = it },
            label = { Text(text = "Numero Factura") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = fechaEmision ?: "",
            onValueChange = { fechaEmision = it },
            label = { Text(text = "Fecha Emisión") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = empresa ?: "",
            onValueChange = { empresa = it },
            label = { Text(text = "Empresa") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = nif ?: "",
            onValueChange = { nif = it },
            label = { Text(text = "Nif") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = direccion ?: "",
            onValueChange = { direccion = it },
            label = { Text(text = "Direccion") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        /*
        OutlinedTextField(
            value = baseImponible ?: "",
            onValueChange = { baseImponible = it },
            label = { Text(text = "BaseImponible") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        * */

        OutlinedTextField(
            value = baseImponibleText,
            onValueChange = {
                baseImponibleText = it
                baseImponible = it.toDoubleOrNull() ?: 0.0  // Convierte a Double o usa 0.0 si falla
                println("Base Imponible: $baseImponible") // Depuración
            },
            label = { Text(text = "Base Imponible") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Solo números
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = ivaText,
            onValueChange = {
                ivaText = it
                iva = it.toDoubleOrNull() ?: 0.0 // Convierte a Double o usa 0.0 si el input es inválido
                println("IVA: $iva") // Depuración
            },
            label = { Text(text = "IVA") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Muestra teclado numérico
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = totalText,
            onValueChange = {
                totalText = it
                total = it.toDoubleOrNull() ?: 0.0 // Convierte a Double o usa 0.0 si el input es inválido
                println("Total: $total") // Depuración
            },
            label = { Text(text = "TOTAL") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // Muestra teclado numérico
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        Button(
            onClick = {
                // usuario = Facturas(usuario = usuario, email = email)
                val facturas = Facturas(numeroFactura = numeroFactura, fechaEmision = fechaEmision, empresa = empresa, nif = nif,
                    direccion = direccion, baseImponible = baseImponible, iva = iva, total = total)

                viewModel.agregarFactura(facturas)
                navController.popBackStack()
            }
        ) {
            Text(text = "Agregar")
        }
    }
}