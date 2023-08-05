package com.example.sushitestapp.utils


data class DataState<T>(
    val error: String? = null,
    val data: T? = null,
) {

    companion object {

        fun <T> error(
            errorMessage: String?,
//            stateEvent: StateEvent
        ): DataState<T> {
            return DataState(
                error = errorMessage,
                data = null,
//                stateEvent = stateEvent
            )
        }

        fun <T> data(
            data: T? = null,
//            stateEvent: StateEvent
        ): DataState<T> {
            return DataState(
                error = null,
                data = data,
//                stateEvent = stateEvent
            )
        }
    }
}























