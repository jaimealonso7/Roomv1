package com.example.roomv1.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomv1.models.Facturas
import kotlinx.coroutines.flow.Flow

@Dao
interface FacturasDatabaseDao {

    @Query("SELECT * FROM facturas")
    fun obtenerUsuarios(): Flow<List<Facturas>>

    @Query("SELECT * FROM facturas WHERE id = :id")
    fun obtenerUsuario(id: Int): Flow<Facturas>

    @Insert
    suspend fun agregarFactura(factura: Facturas)

    @Update
    suspend fun actualizarFactura(factura: Facturas)

    @Delete
    suspend fun borrarFactura(factura: Facturas)

}