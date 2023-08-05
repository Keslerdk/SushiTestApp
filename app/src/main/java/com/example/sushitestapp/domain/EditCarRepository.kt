package com.example.sushitestapp.domain

import com.example.sushitestapp.data.local.models.Car
import com.example.sushitestapp.presentor.edit.state.EditCarViewState
import com.example.sushitestapp.utils.DataState
import com.example.sushitestapp.utils.StateEvent
import kotlinx.coroutines.flow.Flow

interface EditCarRepository {
    fun addNewCar(event: StateEvent, car: Car) : Flow<DataState<EditCarViewState>>

    fun updateCar(event: StateEvent, car: Car) : Flow<DataState<EditCarViewState>>
}