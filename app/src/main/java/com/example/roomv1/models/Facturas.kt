package com.example.roomv1.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Facturas")
data class Facturas(
    val id: String = "",  // Puedes poner un valor predeterminado, ya que Firestore no necesita este campo.
    val numeroFactura: String = "",
    val fechaEmision: String = "",
    val empresa: String = "",
    val nif: String = "",
    val direccion: String = "",
    val baseImponible: Double = 0.0,
    val iva: Double = 0.0,
    val total: Double = 0.0
) {
    // Constructor sin argumentos, requerido por Firestore
    constructor() : this(
        id = "",
        numeroFactura = "",
        fechaEmision = "",
        empresa = "",
        nif = "",
        direccion = "",
        baseImponible = 0.0,
        iva = 0.0,
        total = 0.0
    )
}
