package com.example.sushitestapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val image: String,
    val price: Int,
    val brand: String,
    val model: String,
    val year: Int,
    val mileage : Long,
    val color: String,
    val vin: String
)
