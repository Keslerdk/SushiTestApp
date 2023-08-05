package com.example.sushitestapp.utils

sealed class QueryResult<out T> {
    data class Success<out T>(val value: T) : QueryResult<T>()
    data class SQLiteError(val message: String) : QueryResult<Nothing>()
    data class GeneralError(val id: Long, val message: String) : QueryResult<Nothing>()
}