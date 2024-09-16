package com.zcg.core_data.repository

import com.zcg.core_data.items.ItemSection


interface ItemsCallback {

    fun onSuccess(itemsData: List<ItemSection>)

    fun onError(errorMessage: String)

    fun onNetworkError()

    fun onCancel()
}