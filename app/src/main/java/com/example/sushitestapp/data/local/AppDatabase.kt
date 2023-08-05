package com.example.sushitestapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sushitestapp.data.local.models.Car

@Database(entities = [Car::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
}