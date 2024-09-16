package com.zcg.core_data.repository

import com.zcg.core_data.items.ItemSection
import com.zcg.core_data.remote.NetworkResponse
import com.zcg.core_data.remote.RemoteHelper
import javax.inject.Inject

class ItemsRepository @Inject constructor(
    private val remoteHelper: RemoteHelper
) {


    suspend fun fetchItemsData(
        statusListener: ItemsCallback
    ) {
        val response = remoteHelper.fetchItemsData()

        response.let {
            when (it) {
                is NetworkResponse.Success<List<ItemSection>> -> {
                    val itemSections = it.items
                    itemSections?.let { response ->
                        statusListener.onSuccess(response)
                    }
                }

                is NetworkResponse.Error -> {
                    statusListener.onNetworkError()
                    statusListener.onError("${it.errorCode} - ${it.errorMessage}")
                }

                is NetworkResponse.Exception -> {
                    statusListener.onError("${it.exception.message}")
                }
            }
        }
    }
}