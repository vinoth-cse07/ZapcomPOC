package com.zcg.core_data.remote

import android.content.Context
import okhttp3.Cache
import okhttp3.Handshake
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier

object RemoteServiceBuilder {

    fun build(applicationContext: Context, baseURL: String): APIService {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkhttpClient(applicationContext))
            .build().create(APIService::class.java)
    }

    private fun getOkhttpClient(
        context: Context
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.hostnameVerifier { _, _ -> true }
        client.addInterceptor {
            val original: Request = it.request()
            val request: Request = original.newBuilder()
                .method(original.method, original.body)
                .build()

            it.proceed(request)
        }
        client.cache(getHttpCache(context))
        return client.build()
    }

    private fun getHttpCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize.toLong())
    }
}