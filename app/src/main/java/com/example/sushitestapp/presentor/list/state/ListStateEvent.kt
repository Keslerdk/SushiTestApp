package com.example.sushitestapp.presentor.list.state

import com.example.sushitestapp.utils.StateEvent

sealed class ListStateEvent : StateEvent {
    object GetCars : ListStateEvent() {
        override fun getErrorInfo(): String = "Can't get cars"
        override fun toString(): String = "GetCars"

    }

    object AddNewCar : ListStateEvent() {
        override fun getErrorInfo(): String = "Can't add new car"
        override fun toString(): String = "AddNewCar"

    }

    class SetSelectedCar(val id: Long) : ListStateEvent() {
        override fun getErrorInfo(): String = "Can't select car"
        override fun toString(): String = "SetSelectedCar"
    }

    object SortByPrice : ListStateEvent() {
        override fun getErrorInfo(): String = "Can't sort by price"
        override fun toString(): String = "SortByPrice"

    }

    object GetCategories : ListStateEvent() {
        override fun getErrorInfo(): String = "Can't get categories"
        override fun toString(): String = "GetCategories"
    }

    class FilterByCategory(val category: String) : ListStateEvent() {
        override fun getErrorInfo(): String = "Can't filter by category"
        override fun toString(): String = "FilterByCategory"
    }
}