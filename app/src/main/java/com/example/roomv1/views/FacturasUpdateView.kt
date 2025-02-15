package com.example.roomv1.views

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.roomv1.models.Facturas
import com.example.roomv1.viewmodels.FacturasViewModel

@Composable
fun FacturasUpdateView(navController: NavController, viewModel: FacturasViewModel, id: Int) {
    println("ID recibido: $id") // Verifica el ID recibido

    val factura = viewModel.obtenerFacturaPorId(id)

    if (factura == null) {
        Log.e("FacturasApp", "Factura con ID $id no encontrada")
        Text("Factura no encontrada")
        return
    }

    var baseImponible by remember { mutableStateOf(factura.baseImponible) }
    var iva by remember { mutableStateOf(factura.iva) }
    val total = baseImponible + (baseImponible * iva / 100)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Permite desplazarse si el contenido es largo
    ) {
        // Título centralizado con un tamaño adecuado y color
        Text(
            text = "Editar Factura ID: $id",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        // Campo para Base Imponible
        OutlinedTextField(
            value = baseImponible.toString(),
            onValueChange = { baseImponible = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Base Imponible") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
            singleLine = true
        )

        // Campo para IVA
        OutlinedTextField(
            value = iva.toString(),
            onValueChange = { iva = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("IVA") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
            singleLine = true
        )

        // Total calculado
        Text(
            text = "Total: ${"%.2f".format(total)}€",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Center
        )

        // Botón "Guardar Cambios" con estilo y color
        Button(
            onClick = {
                val updatedFactura = factura.copy(baseImponible = baseImponible, iva = iva, total = total)
                viewModel.actualizarFactura(updatedFactura)
                navController.popBackStack() // Regresa a la lista
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Guardar Cambios",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}


/*@Composable
fun FacturasUpdateView(navController: NavController, viewModel: FacturasViewModel, id: Int) {
    println("ID recibido: $id") // 🔹 Verifica el ID recibido

    val factura = viewModel.obtenerFacturaPorId(id)

    if (factura == null) {
        Log.e("FacturasApp", "⚠️ Factura con ID $id no encontrada")
        Text("Factura no encontrada")
        return
    }

    var baseImponible by remember { mutableStateOf(factura.baseImponible) }
    var iva by remember { mutableStateOf(factura.iva) }
    val total = baseImponible + (baseImponible * iva / 100)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Editar Factura ID: $id") // Mostrar en pantalla

        TextField(
            value = baseImponible.toString(),
            onValueChange = { baseImponible = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Base Imponible") }
        )
        TextField(
            value = iva.toString(),
            onValueChange = { iva = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("IVA") }
        )

        Text(text = "Total: ${"%.2f".format(total)}€")

        Button(onClick = {
            val updatedFactura = factura.copy(baseImponible = baseImponible, iva = iva, total = total)
            viewModel.actualizarFactura(updatedFactura)
            navController.popBackStack()
        }) {
            Text("Guardar Cambios")
        }
    }
}*/