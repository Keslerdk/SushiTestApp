package com.example.sushitestapp.presentor.list.state

import com.example.sushitestapp.presentor.models.CarCard

data class ListViewState(
    val cars: List<CarCard>? = null,
    val isLoading: Boolean = false,
    val activeJobCounter: HashSet<String> = HashSet(),
)