package com.example.roomv1.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
                    Text(text = "Pantalla de Inicio", color = Color.Black, fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Red
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route= "FacturasAddView") },
                containerColor = Color.Red,
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
    /*var baseImponible by remember { mutableStateOf(0.0) } // Hacer iva mutable
    var baseImponibleText by remember { mutableStateOf(baseImponible.toString()) }
    var iva by remember { mutableStateOf(0.0) } // Hacer iva mutable
    var ivaText by remember { mutableStateOf(iva.toString()) }
    var total by remember { mutableStateOf(0.0) }
    var totalText by remember { mutableStateOf(total.toString()) }*/

    var baseImponible by remember { mutableStateOf(baseImponible) } // Asigna el valor pasado al composable
    var iva by remember { mutableStateOf(iva) }
    var total by remember { mutableStateOf(total) }

    Column(
        modifier = Modifier.padding(it)
    ) {
        LazyColumn {
            items(state.facturasList) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        Text(text = it.numeroFactura)
                        Text(text = it.fechaEmision)
                        Text(text = it.empresa)
                        Text(text = it.nif)
                        Text(text = it.direccion)
                        Text(text = it.baseImponible.toString())
                        Text(text = it.iva.toString())
                        Text(text = it.total.toString())
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