package com.example.sushitestapp.utils

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    @Suppress("unused")
    fun peekContent(): T = content

    fun peekNotHandledContent(): T? = if (hasBeenHandled) null else content
}