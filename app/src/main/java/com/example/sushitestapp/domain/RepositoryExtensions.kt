package com.example.sushitestapp.domain

import android.database.sqlite.SQLiteException
import com.example.sushitestapp.data.local.models.Car
import com.example.sushitestapp.utils.QueryResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> safeQuery(
    dispatcher: CoroutineDispatcher,
    query: suspend () -> T?
): QueryResult<T?> =
    withContext(dispatcher) {
        try {
            QueryResult.Success(query.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is SQLiteException -> QueryResult.SQLiteError(throwable.message ?: "")
                else -> QueryResult.GeneralError(-1, throwable.message ?: "")
            }
        }
    }
