package com.zcg.core_data.remote

sealed class NetworkResponse<out T : Any> {

    data class Success<T : Any>(val items: T?) : NetworkResponse<T>()

    data class Error(val errorCode: Int, val errorMessage: String) : NetworkResponse<Nothing>()

    data class Exception(val exception: kotlin.Exception) : NetworkResponse<Nothing>()
}