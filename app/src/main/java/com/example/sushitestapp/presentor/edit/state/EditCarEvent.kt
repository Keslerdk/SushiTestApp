package com.example.sushitestapp.presentor.edit.state

import com.example.sushitestapp.data.local.models.Car
import com.example.sushitestapp.utils.StateEvent

sealed class EditCarEvent : StateEvent{
    class AddNewCar(val car: Car) : EditCarEvent() {
        override fun getErrorInfo(): String = "Can't add new car"
        override fun toString(): String  = "AddNewCar"

    }

    class UpdateCar(val car: Car) : EditCarEvent() {
        override fun getErrorInfo(): String = "Can't update car"
        override fun toString(): String  = "UpdateCar"
    }

}