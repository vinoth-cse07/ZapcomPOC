package com.zcg.core_data.remote

import android.content.Context
import com.zcg.core_data.items.ItemSection
import javax.inject.Inject

class RemoteHelper @Inject constructor(
    applicationContext: Context
) {
    private var service: APIService = RemoteServiceBuilder.build(applicationContext, "https://jsonkeeper.com/b/")

    suspend fun fetchItemsData(
    ): NetworkResponse<List<ItemSection>> {
        return ItemsDataStore.getItemsData(service)
    }
}