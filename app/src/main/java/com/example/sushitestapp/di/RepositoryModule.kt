package com.example.sushitestapp.di

import android.content.Context
import androidx.room.Room
import com.example.sushitestapp.data.CarListRepositoryImpl
import com.example.sushitestapp.data.EditCarRepositoryImpl
import com.example.sushitestapp.data.local.AppDatabase
import com.example.sushitestapp.domain.CarListRepository
import com.example.sushitestapp.domain.EditCarRepository
import com.example.sushitestapp.utils.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCarDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db").build()


    @Provides
    @Singleton
    fun provideCarListRepository(db: AppDatabase, mapper: Mapper): CarListRepository =
        CarListRepositoryImpl(db, mapper)

    @Provides
    @Singleton
    fun provideEditCarRepository(db: AppDatabase) : EditCarRepository = EditCarRepositoryImpl(db)
}