package com.zcg.core_usecase.items

import com.zcg.core_data.items.ItemSection
import com.zcg.core_data.repository.ItemsCallback
import com.zcg.core_data.repository.ItemsRepository
import javax.inject.Inject

class ItemsUseCase @Inject constructor(
    private val itemsRepository: ItemsRepository
) {


    suspend fun fetchItems(
        statusListener: ItemsCallback
    ) {
        itemsRepository.fetchItemsData(
            object : ItemsCallback {
                override fun onSuccess(itemsData: List<ItemSection>) {
                    statusListener.onSuccess(itemsData)
                }

                override fun onError(errorMessage: String) {
                    statusListener.onError(errorMessage)
                }

                override fun onNetworkError() {
                    statusListener.onNetworkError()
                }

                override fun onCancel() {
                    statusListener.onCancel()
                }
            }
        )
    }
}