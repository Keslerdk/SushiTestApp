package com.example.sushitestapp.data

import com.example.sushitestapp.data.local.AppDatabase
import com.example.sushitestapp.data.local.models.Car
import com.example.sushitestapp.domain.CarListRepository
import com.example.sushitestapp.domain.safeQuery
import com.example.sushitestapp.presentor.list.state.ListViewState
import com.example.sushitestapp.utils.DataState
import com.example.sushitestapp.utils.Mapper
import com.example.sushitestapp.utils.ResponseHandler
import com.example.sushitestapp.utils.StateEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CarListRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val mapper: Mapper
) : CarListRepository {

    override fun fetchCars(event: StateEvent): Flow<DataState<ListViewState>> = flow {
        val response = safeQuery(Dispatchers.IO) { db.carDao().getAll() }
        emit(object : ResponseHandler<ListViewState, List<Car>>(response, event) {
            override fun handleSuccess(resultObj: List<Car>): DataState<ListViewState> =
                DataState.data(ListViewState(cars = mapper.mapListToPresentor(resultObj)))
        }.getResult())

    }
}