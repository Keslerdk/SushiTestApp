package com.example.sushitestapp.presentor.list.state

import com.example.sushitestapp.presentor.models.CarCard

data class ListViewState(
    val cars: List<CarCard>? = null,
    val isSortedByPrice : Boolean= false,
    val categories: Map<Int, String>? = null,
    val selectedCar : Long = -1L,
    val isLoading: Boolean = false,
    val activeJobCounter: HashSet<String> = HashSet(),
)