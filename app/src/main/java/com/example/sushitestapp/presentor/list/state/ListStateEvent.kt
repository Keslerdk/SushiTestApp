package com.example.sushitestapp.presentor.list.state

import com.example.sushitestapp.utils.StateEvent

sealed class ListStateEvent : StateEvent {
    class GetCars() : ListStateEvent() {
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
}