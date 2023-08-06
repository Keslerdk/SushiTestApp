package com.example.sushitestapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sushitestapp.data.local.models.Car
import com.example.sushitestapp.data.local.models.CategoryTuple

@Dao
interface CarDao {
    @Query("select * from cars")
    suspend fun getAll(): List<Car>

    @Query("select * from cars order by cars.price")
    suspend fun getCarsSortedByPrice() : List<Car>

    @Query("select * from cars where cars.id = :id")
    suspend fun getCarById(id: Long) : Car

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCar(car: Car) : Long

    @Update
    suspend fun updateCar(car: Car) : Int

    @Query("select * from cars where cars.brand = :category or cars.model = :category")
    suspend fun getCarsByBrandAndModel(category: String): List<Car>

    @Query("select cars.model, cars.brand from cars")
    suspend fun getBrandAndModels() : List<CategoryTuple>

}