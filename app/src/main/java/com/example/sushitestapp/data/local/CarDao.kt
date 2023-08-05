package com.example.sushitestapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sushitestapp.data.local.models.Car

@Dao
interface CarDao {
    @Query("select * from cars")
    suspend fun getAll(): List<Car>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCar(car: Car) : Long

    @Update
    suspend fun updateCar(car: Car) : Int

    @Query("select * from cars where cars.brand = :brand or cars.model = :model")
    suspend fun getCarsByBrandAndModel(brand: String, model: String): List<Car>

    @Query("select * from cars order by cars.price")
    suspend fun getCarsSortByPrice(): List<Car>
}