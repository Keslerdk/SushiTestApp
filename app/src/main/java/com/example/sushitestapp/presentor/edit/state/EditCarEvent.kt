package com.example.sushitestapp.presentor.edit.state

import com.example.sushitestapp.data.local.models.Car
import com.example.sushitestapp.utils.StateEvent

sealed class EditCarEvent : StateEvent{

    class GetSelectedCar(val id: Long) : EditCarEvent() {
        override fun getErrorInfo(): String = "Can't get selected car."
        override fun toString(): String  = "GetSelectedCar"

    }

    class SaveChanges(val car: Car, val id: Long) : EditCarEvent() {
        override fun getErrorInfo(): String = "Can't save car"
        override fun toString(): String  = "SaveChanges"
    }

}