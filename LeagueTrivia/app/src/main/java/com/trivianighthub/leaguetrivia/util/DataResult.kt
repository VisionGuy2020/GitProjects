package com.trivianighthub.leaguetrivia.util

sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()
    data class Error(val message: String, val exception: Exception? = null) : DataResult<Nothing>()
    object Loading : DataResult<Nothing>()
} 