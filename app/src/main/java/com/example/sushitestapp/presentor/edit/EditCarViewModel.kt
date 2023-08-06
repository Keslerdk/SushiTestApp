package com.example.sushitestapp.presentor.edit

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sushitestapp.domain.EditCarRepository
import com.example.sushitestapp.presentor.edit.state.EditCarEvent
import com.example.sushitestapp.presentor.edit.state.EditCarViewState
import com.example.sushitestapp.utils.Constants
import com.example.sushitestapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EditCarViewModel @Inject constructor(private val repository: EditCarRepository) :
    ViewModel() {
    private val _viewState = MutableLiveData<EditCarViewState>()
    val viewState: LiveData<EditCarViewState> get() = _viewState

    fun handleEvent(event: EditCarEvent) {
        when (event) {
            is EditCarEvent.GetSelectedCar -> launchJob(
                event.toString(),
                repository.getSelectedCar(event, event.id)
            )

            is EditCarEvent.SaveChanges -> launchJob(
                event.toString(),
                if (event.id != Constants.NON_CAR_SELECTED_ID)
                    repository.updateCar(event, event.car.copy(id = event.id))
                else repository.addNewCar(event, event.car)
            )
        }
    }

    private fun launchJob(eventName: String, jobFun: Flow<DataState<EditCarViewState>>) {
        if (!isJobAlreadyActive(eventName)) {
            addJobCounter(eventName)
            _viewState.value = getCurrentViewStateOrNew().copy(isLoading = true)
            jobFun.onEach { dataState ->
                dataState.data?.let { viewState ->
                    viewState.car?.let {
                        _viewState.value = getCurrentViewStateOrNew().copy(car = it)
                    }

                    _viewState.value =
                        getCurrentViewStateOrNew().copy(isChangesSaved = viewState.isChangesSaved)
                }

                dataState.error?.let {
                    Log.e(ContentValues.TAG, "launchJob: job error: $it")
                }
                removeJobCounter(eventName)
                _viewState.value =
                    getCurrentViewStateOrNew().copy(isLoading = false)
            }.launchIn(viewModelScope)
        }
    }

    //    helper region
    private fun getCurrentViewStateOrNew() = viewState.value ?: EditCarViewState()

    private fun isJobAlreadyActive(name: String): Boolean =
        getCurrentViewStateOrNew().activeJobCounter.contains(name)

    private fun addJobCounter(name: String) {
        val viewState = getCurrentViewStateOrNew().apply {
            activeJobCounter.add(name)
        }
        _viewState.value = viewState
    }

    private fun removeJobCounter(name: String) {
        val viewState = getCurrentViewStateOrNew().apply {
            activeJobCounter.remove(name)
        }
        _viewState.value = viewState
    }
}