package com.example.roomv1.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun FacturasUpdateView(
    navController: NavController,
    viewModel: FacturasViewModel,
    id: Int,  // Agregamos el ID para poder actualizar correctamente
    numeroFactura: String,
    fechaEmision: String,
    empresa: String,
    nif: String,
    direccion: String,
    baseImponible: Double,
    iva: Double,
    total: Double
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Actualizar Factura", color = Color.White, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Red),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Regresar", tint = Color.White)
                    }
                }
            )
        }
    ) {
        ContentUpdateView(it, navController, viewModel, id, numeroFactura, fechaEmision, empresa, nif, direccion, baseImponible, iva, total)
    }
}

@Composable
fun ContentUpdateView(
    it: PaddingValues,
    navController: NavController,
    viewModel: FacturasViewModel,
    id: Int,
    numeroFactura: String,
    fechaEmision: String,
    empresa: String,
    nif: String,
    direccion: String,
    baseImponible: Double,
    iva: Double,
    total: Double
) {
    var numeroFactura by remember { mutableStateOf(numeroFactura) }
    var fechaEmision by remember { mutableStateOf(fechaEmision) }
    var empresa by remember { mutableStateOf(empresa) }
    var nif by remember { mutableStateOf(nif) }
    var direccion by remember { mutableStateOf(direccion) }
    var baseImponible by remember { mutableStateOf(baseImponible) }
    var iva by remember { mutableStateOf(iva) }
    var total by remember { mutableStateOf(total) }

    var baseImponibleText by remember { mutableStateOf(baseImponible.toString()) }
    var ivaText by remember { mutableStateOf(iva.toString()) }
    var totalText by remember { mutableStateOf(total.toString()) }

    var errorMessage by remember { mutableStateOf("") } // Mensaje de error para validaciones

    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = numeroFactura,
            onValueChange = { numeroFactura = it },
            label = { Text(text = "Número Factura") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        OutlinedTextField(
            value = fechaEmision,
            onValueChange = { fechaEmision = it },
            label = { Text(text = "Fecha Emisión") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        OutlinedTextField(
            value = empresa,
            onValueChange = { empresa = it },
            label = { Text(text = "Empresa") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        OutlinedTextField(
            value = nif,
            onValueChange = { nif = it },
            label = { Text(text = "NIF") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text(text = "Dirección") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        OutlinedTextField(
            value = baseImponibleText,
            onValueChange = {
                baseImponibleText = it
                baseImponible = it.toDoubleOrNull() ?: 0.0
            },
            label = { Text(text = "Base Imponible") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        OutlinedTextField(
            value = ivaText,
            onValueChange = {
                ivaText = it
                iva = it.toDoubleOrNull() ?: 0.0
            },
            label = { Text(text = "IVA") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        OutlinedTextField(
            value = totalText,
            onValueChange = {
                totalText = it
                total = it.toDoubleOrNull() ?: 0.0
            },
            label = { Text(text = "Total") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 8.dp)
        )

        // Muestra un mensaje de error si los valores son incorrectos
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red, modifier = Modifier.padding(8.dp))
        }

        Button(
            onClick = {
                // Validaciones antes de actualizar
                if (numeroFactura.isEmpty() || empresa.isEmpty() || nif.isEmpty() || direccion.isEmpty() || fechaEmision.isEmpty()) {
                    errorMessage = "Todos los campos deben estar llenos."
                } else if (baseImponible <= 0 || iva < 0 || total <= 0) {
                    errorMessage = "Los valores numéricos deben ser mayores que 0."
                } else {
                    errorMessage = "" // Limpia el mensaje de error
                    val facturaActualizada = Facturas(
                        id = id,
                        numeroFactura = numeroFactura,
                        fechaEmision = fechaEmision,
                        empresa = empresa,
                        nif = nif,
                        direccion = direccion,
                        baseImponible = baseImponible,
                        iva = iva,
                        total = total
                    )

                    viewModel.actualizarFactura(facturaActualizada) // Llamamos a la actualización
                    navController.popBackStack() // Volvemos atrás
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Guardar Cambios")
        }
    }
}
