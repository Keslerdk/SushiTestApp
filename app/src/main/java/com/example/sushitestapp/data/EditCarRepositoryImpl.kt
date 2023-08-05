package com.example.sushitestapp.data

import com.example.sushitestapp.data.local.AppDatabase
import com.example.sushitestapp.data.local.models.Car
import com.example.sushitestapp.domain.EditCarRepository
import com.example.sushitestapp.domain.safeQuery
import com.example.sushitestapp.presentor.edit.state.EditCarViewState
import com.example.sushitestapp.utils.DataState
import com.example.sushitestapp.utils.ResponseHandler
import com.example.sushitestapp.utils.StateEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EditCarRepositoryImpl @Inject constructor(private val database: AppDatabase) :
    EditCarRepository {
    override fun addNewCar(event: StateEvent, car: Car): Flow<DataState<EditCarViewState>> = flow {
        val response = safeQuery(Dispatchers.IO) { database.carDao().addCar(car) }
        emit(object : ResponseHandler<EditCarViewState, Long>(response, event) {
            override fun handleSuccess(resultObj: Long): DataState<EditCarViewState> {
                return if (resultObj > 0) {
                    DataState.data(EditCarViewState(isLoading = false))
                } else {
                    DataState.error("Something gone wrong")
                }
            }

        }.getResult())
    }

    override fun updateCar(event: StateEvent, car: Car): Flow<DataState<EditCarViewState>> = flow {
        val response = safeQuery(Dispatchers.IO) { database.carDao().updateCar(car) }
        emit(object : ResponseHandler<EditCarViewState, Int>(response, event) {
            override fun handleSuccess(resultObj: Int): DataState<EditCarViewState> {
                return if (resultObj > 0) {
                    DataState.data(EditCarViewState(isLoading = false))
                } else {
                    DataState.error("Something gone wrong")
                }
            }

        }.getResult())
    }
}