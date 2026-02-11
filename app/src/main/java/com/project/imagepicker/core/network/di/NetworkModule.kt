package com.project.imagepicker.core.network.di

import com.project.imagepicker.BuildConfig
import com.project.imagepicker.core.network.NetworkConfig
import com.project.imagepicker.core.network.api.ImageApi
import com.project.imagepicker.core.network.clients.createDefaultHttpClient
import com.project.imagepicker.core.network.clients.createDefaultRetrofitClient
import com.project.imagepicker.core.network.interceptors.ApiKeyInterceptor
import com.project.imagepicker.core.network.interceptors.createHttpLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkConfig() = NetworkConfig()


    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        Timber.d("AAAA PIXABAY_API_KEY = ${BuildConfig.PIXABAY_API_KEY}")
        return ApiKeyInterceptor(BuildConfig.PIXABAY_API_KEY)
    }

    @Provides
    @Singleton
    fun provideOhHttp(
        networkConfig: NetworkConfig,
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {
        return createDefaultHttpClient(
            networkConfig.timeout,
            listOf(
                createHttpLoggingInterceptor(networkConfig.isDebug),
                apiKeyInterceptor
            )
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        networkConfig: NetworkConfig,
        client: OkHttpClient
    ): Retrofit {
        return createDefaultRetrofitClient(
            networkConfig.baseUrl,
            client
        )
    }

    @Provides
    @Singleton
    fun providePixabayApi(retrofit: Retrofit): ImageApi {
        return retrofit.create()
    }

}