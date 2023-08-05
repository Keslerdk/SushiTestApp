package com.example.sushitestapp.utils

import android.content.Context
import com.example.sushitestapp.R
import com.example.sushitestapp.data.local.models.Car
import com.example.sushitestapp.presentor.models.CarCard
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Mapper @Inject constructor(@ApplicationContext private val context: Context) {
    private fun Car.mapToPresentor(): CarCard =
        CarCard(
            id = id ?: -1L,
            image = image,
            color = color,
            name = brand + model,
            vin = vin,
            status = "status",
            miles = context.getString(R.string.car_list_miles, mileage.toString()),
            price = "$price $"
        )

    fun mapListToPresentor(list: List<Car>): List<CarCard> = list.map { it.mapToPresentor() }

}

