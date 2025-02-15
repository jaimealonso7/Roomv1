package com.example.roomv1.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
            dao.obtenerUsuarios().collectLatest { facturas ->
                // Aquí aseguramos que actualizamos el estado con las facturas cargadas
                state = state.copy(facturasList = facturas)
            }
        }
    }


    fun agregarFactura(facturas: Facturas) = viewModelScope.launch {
        dao.agregarFactura(factura = facturas)
    }

    private val _facturasList = mutableStateListOf<Facturas>()
    val facturasList: List<Facturas> get() = _facturasList

    /* Este método actualiza una factura específica
    fun actualizarFactura(factura: Facturas) {
        val index = _facturasList.indexOfFirst { it.id == factura.id }
        if (index != -1) {
            // Reemplazar la factura con los nuevos valores
            _facturasList[index] = factura.copy() // Asegúrate de que se reemplace completamente
        }
    }*/

    fun actualizarFactura(factura: Facturas) = viewModelScope.launch {
        dao.actualizarFactura(factura)
    }

    fun borrarFactura(facturas: Facturas) = viewModelScope.launch {
        dao.borrarFactura(factura = facturas)
    }

    fun obtenerFacturaPorId(id: Int): Facturas? {
        return state.facturasList.find { it.id == id }
    }

}