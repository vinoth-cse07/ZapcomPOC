package com.zcg.core_data.remote

import com.zcg.core_data.items.ItemSection

import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("5BEJ")
    suspend fun getItemsData(): Response<List<ItemSection>>
}