package com.example.sushitestapp.utils

import com.example.sushitestapp.utils.Constants.SQLITE_ERROR
import com.example.sushitestapp.utils.Constants.UNKNOWN_ERROR

abstract class ResponseHandler<ViewState, Data>(
    private val response: QueryResult<Data?>,
    private val stateEvent: StateEvent
) {

    fun getResult(): DataState<ViewState> = when (response) {

        is QueryResult.GeneralError -> {
            DataState.error(
                errorMessage = stateEvent.getErrorInfo()
                        + "\n\nReason: " + response.message,
//                stateEvent = stateEvent
            )
        }

        is QueryResult.SQLiteError -> {
            DataState.error(
                errorMessage = stateEvent.getErrorInfo()
                        + "\n\nReason: " + SQLITE_ERROR,
//                stateEvent = stateEvent
            )
        }

        is QueryResult.Success -> {
            if (response.value == null) {
                DataState.error(
                    errorMessage = stateEvent.getErrorInfo()
                            + "\n\nReason: " + UNKNOWN_ERROR,
//                    stateEvent = stateEvent
                )
            } else {
                handleSuccess(resultObj = response.value)
            }
        }

    }

    abstract fun handleSuccess(resultObj: Data): DataState<ViewState>

}