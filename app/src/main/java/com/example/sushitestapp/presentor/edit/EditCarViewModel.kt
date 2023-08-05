package com.example.sushitestapp.presentor.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sushitestapp.domain.EditCarRepository
import com.example.sushitestapp.presentor.edit.state.EditCarEvent
import com.example.sushitestapp.presentor.edit.state.EditCarViewState
import com.example.sushitestapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class EditCarViewModel @Inject constructor(private val repository: EditCarRepository) : ViewModel() {
    private val _viewState = MutableLiveData<EditCarViewState>()
    val viewState: LiveData<EditCarViewState> get() = _viewState

    fun handleEvent(event: EditCarEvent) {
        when (event) {
            is EditCarEvent.AddNewCar -> launchJob(
                event.toString(),
                repository.addNewCar(event, event.car)
            )

            is EditCarEvent.UpdateCar -> launchJob(
                event.toString(),
                repository.updateCar(event, event.car)
            )
        }
    }

    private fun launchJob(eventName: String, jobFun: Flow<DataState<EditCarViewState>>) {
        if (!isJobAlreadyActive(eventName)) {
            addJobCounter(eventName)
            jobFun.launchIn(viewModelScope)
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

}