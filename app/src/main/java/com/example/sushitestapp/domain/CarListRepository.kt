package com.example.sushitestapp.domain

import com.example.sushitestapp.presentor.list.state.ListViewState
import com.example.sushitestapp.utils.DataState
import com.example.sushitestapp.utils.StateEvent
import kotlinx.coroutines.flow.Flow

interface CarListRepository {
    fun fetchCars(event: StateEvent): Flow<DataState<ListViewState>>

    fun sortByPrice(event: StateEvent): Flow<DataState<ListViewState>>

    fun getCategories(event: StateEvent) : Flow<DataState<ListViewState>>

    fun getCarsByCategory(event: StateEvent, category: String): Flow<DataState<ListViewState>>

}