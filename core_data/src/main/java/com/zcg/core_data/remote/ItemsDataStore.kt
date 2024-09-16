package com.zcg.core_data.remote

import android.util.Log
import com.zcg.core_data.items.ItemSection

object ItemsDataStore {
    private val TAG = ItemsDataStore::class.java.simpleName
    private suspend fun fetch(
        apiService: APIService
    ): NetworkResponse<List<ItemSection>> {

        try {
            val result = apiService.getItemsData()
            if (result.isSuccessful) {
                return NetworkResponse.Success(result.body())
            }
            return NetworkResponse.Error(result.code(), result.message())
        } catch (e: Exception) {
            Log.e(TAG, e.stackTraceToString())
            return NetworkResponse.Error(-1, e.message ?: "Network Error")
        }

    }

    suspend fun getItemsData(
        apiService: APIService
    ): NetworkResponse<List<ItemSection>> {

        return APIHandler.fetch(
            apiFunction = { fetch(apiService) },
            errorMessage = "Unable to fetch Items Data!!"
        )
    }
}