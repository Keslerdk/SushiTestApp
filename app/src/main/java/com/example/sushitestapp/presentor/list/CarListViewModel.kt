package com.example.sushitestapp.presentor.list

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sushitestapp.R
import com.example.sushitestapp.domain.CarListRepository
import com.example.sushitestapp.presentor.list.state.ListStateEvent
import com.example.sushitestapp.presentor.list.state.ListViewState
import com.example.sushitestapp.utils.DataState
import com.example.sushitestapp.utils.Event
import com.example.sushitestapp.utils.EventLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(private val repository: CarListRepository) :
    ViewModel() {
    private val _viewState = MutableLiveData<ListViewState>()
    val viewState: LiveData<ListViewState> get() = _viewState

    val navigationEvent = EventLiveData<Int>()

    fun handleStateEvent(event: ListStateEvent) {
        when (event) {
            is ListStateEvent.GetCars -> launchJob(event, repository.fetchCars(event))
            is ListStateEvent.AddNewCar -> navigationEvent.value = Event(R.id.ToCarDetailsFragment)
            is ListStateEvent.OpenCarDetails -> navigationEvent.value =
                Event(R.id.ToCarDetailsFragment)
        }
        _viewState.value = getCurrentViewStateOrNew().copy(isLoading = true)
    }

    private fun launchJob(event: ListStateEvent, jobFunction: Flow<DataState<ListViewState>>) {
        if (!isJobAlreadyActive(event.toString())) {
            addJobCounter(event.toString())
            jobFunction.onEach { dataState ->
                dataState.data?.let {
                    _viewState.value =
                        getCurrentViewStateOrNew().copy(cars = it.cars, isLoading = false)
                }
                dataState.error?.let {
                    Log.e(TAG, "launchJob: job error: $it")
                }
                removeJobCounter(event.toString())
            }.launchIn(viewModelScope)
        }
    }


    //    helper region
    private fun getCurrentViewStateOrNew() = viewState.value ?: ListViewState()

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