package com.example.sushitestapp.presentor.edit.state

import com.example.sushitestapp.data.local.models.Car

//data class EditCardState(
//    val imageValue: String? = null,
//    val priceValue: Long? = null,
//    val brandValue: String? = null,
//    val modelValue: String? = null,
//    val yearValue: String? = null,
//    val statusValue: String? = null,
//    val miles: Long? = null,
//    val color: String? = null,
//    val vin: String? = null
//)

data class EditCarViewState(
    val car: Car? = null,
    val isChangesSaved: Boolean = false,
    val isLoading: Boolean = false,
    val activeJobCounter: HashSet<String> = HashSet(),
)