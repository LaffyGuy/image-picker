package com.project.imagepicker.core.network.clients

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import kotlin.time.Duration

fun createDefaultHttpClient(
    timeout: Duration,
    interceptors: Iterable<Interceptor>
): OkHttpClient {
    return OkHttpClient.Builder()
        .callTimeout(timeout)
        .apply {
            interceptors.forEach(::addInterceptor)
        }
        .build()
}