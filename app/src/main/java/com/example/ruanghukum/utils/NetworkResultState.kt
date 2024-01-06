package com.example.ruanghukum.utils

sealed class NetworkResultState<out R> private constructor() {
    data class Success<out T>(val data: T) : NetworkResultState<T>()
    data class Error(val error: String) : NetworkResultState<Nothing>()
    object Loading : NetworkResultState<Nothing>()
}