package com.example.roomv1.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomv1.models.Facturas

@Database(
    entities = [Facturas::class],
    version = 1,
    exportSchema = false
)

abstract class FacturasDatabase: RoomDatabase() {
    abstract fun facturasDao(): FacturasDatabaseDao
}