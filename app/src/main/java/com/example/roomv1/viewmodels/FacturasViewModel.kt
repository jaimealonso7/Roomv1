package com.example.roomv1.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomv1.models.Facturas
import com.example.roomv1.room.FacturasDatabaseDao
import com.example.roomv1.states.FacturasState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FacturasViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val facturasCollection = db.collection("facturas") // Aquí la colección en Firestore

    var state by mutableStateOf(FacturasState())
        private set

    init {
        // Obtén las facturas al inicializar el ViewModel
        viewModelScope.launch {
            cargarFacturas()
        }
    }

    // Función para cargar todas las facturas desde Firestore
    private suspend fun cargarFacturas() {
        try {
            val querySnapshot = facturasCollection.orderBy("id", Query.Direction.ASCENDING).get().await()
            val facturas = querySnapshot.documents.mapNotNull { document ->
                // Cambié el tipo de id a String, ya que Firestore usa String para los IDs
                document.toObject(Facturas::class.java)?.copy(id = document.id) // Firestore usa el ID como String
            }
            Log.d("FacturasViewModel", "Facturas cargadas: $facturas")  // 🔹 Verificación de que las facturas se cargaron
            state = state.copy(facturasList = facturas)
        } catch (e: Exception) {
            Log.e("FacturasViewModel", "Error al cargar las facturas", e)
        }
    }

    // Función para agregar una factura en Firestore
    fun agregarFactura(factura: Facturas) = viewModelScope.launch {
        try {
            // Agregar factura a Firestore
            val facturaRef = facturasCollection.add(factura).await()
            // Si se agrega exitosamente, agregamos la factura a la lista local
            state = state.copy(facturasList = state.facturasList + factura.copy(id = facturaRef.id)) // El ID es String
        } catch (e: Exception) {
            Log.e("FacturasViewModel", "Error al agregar factura", e)
        }
    }

    // Función para actualizar una factura en Firestore
    fun actualizarFactura(factura: Facturas) = viewModelScope.launch {
        try {
            // Actualizamos el documento en Firestore utilizando su ID (de tipo String)
            val facturaRef = facturasCollection.document(factura.id)
            facturaRef.set(factura).await()
            // Actualizamos la lista local de facturas
            state = state.copy(facturasList = state.facturasList.map { if (it.id == factura.id) factura else it })
        } catch (e: Exception) {
            Log.e("FacturasViewModel", "Error al actualizar factura", e)
        }
    }

    // Función para borrar una factura en Firestore
    fun borrarFactura(factura: Facturas) = viewModelScope.launch {
        try {
            // Borra la factura de Firestore usando el ID (de tipo String)
            val facturaRef = facturasCollection.document(factura.id)
            facturaRef.delete().await()
            // Actualizamos la lista local de facturas
            state = state.copy(facturasList = state.facturasList.filter { it.id != factura.id })
        } catch (e: Exception) {
            Log.e("FacturasViewModel", "Error al borrar factura", e)
        }
    }

    // Función para obtener una factura por ID
    fun obtenerFacturaPorId(id: String): Facturas? {
        return state.facturasList.find { it.id == id }
    }
}




/*class FacturasViewModel (
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


    fun actualizarFactura(factura: Facturas) = viewModelScope.launch {
        dao.actualizarFactura(factura)
    }

    fun borrarFactura(facturas: Facturas) = viewModelScope.launch {
        dao.borrarFactura(factura = facturas)
    }

    fun obtenerFacturaPorId(id: Int): Facturas? {
        return state.facturasList.find { it.id == id }
    }

}*/