package com.project.imagepicker.core.network.clients

import com.project.imagepicker.core.network.NetworkConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createDefaultRetrofitClient(
    baseUrl: String,
    client: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}