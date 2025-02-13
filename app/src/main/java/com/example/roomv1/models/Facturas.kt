package com.example.roomv1.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Facturas")
data class Facturas (

    /*@PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("usuario")
    val usuario: String,
    @ColumnInfo("email")
    val email: String*/

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val numeroFactura: String,
    val fechaEmision: String,
    val empresa: String,
    val nif: String,
    val direccion: String,

    val baseImponible: Double,
    val iva: Double,
    val total: Double

)
