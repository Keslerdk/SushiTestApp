package com.example.sushitestapp.presentor.models

data class CarCard(
    val id: Long,
    val image: String,
    val color: String,
    val name: String,
    val vin: String,
    val miles: String,
    val price: String
) {
    override fun toString(): String {
        return "CarCard(image='$image', color='$color', name='$name', vin='$vin', miles='$miles', price='$price')"
    }
}
