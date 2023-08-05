package com.example.sushitestapp.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class EventLiveData<T> : MutableLiveData<Event<T>>() {

    inline fun observeEvent(
        viewLifecycleOwner: LifecycleOwner,
        crossinline observer: (T?) -> Unit
    ) {
        this.observe(viewLifecycleOwner) { t ->
            t.getContentIfNotHandled()?.apply { observer.invoke(this) }
        }
    }
}