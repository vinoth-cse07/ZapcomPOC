package com.zcg.core_data.remote

import android.util.Log
import retrofit2.HttpException

object APIHandler {

    suspend fun <T : Any> fetch(
        apiFunction : suspend () -> NetworkResponse<T>,
        errorMessage: String
    ): NetworkResponse<T> {
        return try {
            apiFunction()
        } catch (e: HttpException) {
            Log.e(e.message(), errorMessage)
            NetworkResponse.Exception(e)
        }
    }
}
