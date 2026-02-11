package com.project.imagepicker.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

fun createHttpLoggingInterceptor(isDebug: Boolean): Interceptor {
    val level = if (isDebug) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.BASIC
    }
    return HttpLoggingInterceptor().setLevel(level)
}