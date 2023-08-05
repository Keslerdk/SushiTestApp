package com.example.sushitestapp.presentor.list.state

import com.example.sushitestapp.utils.StateEvent

sealed class ListStateEvent : StateEvent {
    class GetCars() : ListStateEvent() {
        override fun getErrorInfo(): String = "Can't get cars"
        override fun toString(): String = "GetCars"


    }
    class AddNewCar() : ListStateEvent() {
        override fun getErrorInfo(): String = "Can't add new car"
        override fun toString(): String = "AddNewCar"

    }
    class OpenCarDetails() : ListStateEvent() {
        override fun getErrorInfo(): String = "Can't open car details"
        override fun toString(): String = "OpenCarDetails"

    }
}