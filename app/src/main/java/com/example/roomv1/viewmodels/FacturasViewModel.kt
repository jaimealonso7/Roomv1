package com.example.roomv1.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomv1.models.Facturas
import com.example.roomv1.room.FacturasDatabaseDao
import com.example.roomv1.states.FacturasState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FacturasViewModel (
    private val dao: FacturasDatabaseDao
): ViewModel() {

    var state by mutableStateOf(FacturasState())

        private set

    init {
        viewModelScope.launch {
            dao.obtenerUsuarios().collectLatest {
                state = state.copy(
                    facturasList = it
                )
            }
        }
    }

    fun agregarFactura(facturas: Facturas) = viewModelScope.launch {
        dao.agregarFactura(factura = facturas)
    }

    fun actualizarFactura(facturas: Facturas) = viewModelScope.launch {
        dao.actualizarFactura(factura = facturas)
    }

    fun borrarFactura(facturas: Facturas) = viewModelScope.launch {
        dao.borrarFactura(factura = facturas)
    }

}