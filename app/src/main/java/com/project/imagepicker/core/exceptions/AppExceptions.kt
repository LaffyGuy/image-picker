package com.project.imagepicker.core.exceptions

import retrofit2.HttpException
import java.io.IOException

sealed class AppExceptions(msg: String): Exception(msg) {

    class NetworkException: AppExceptions("No internet connection")
    class ServerException(val code: Int): AppExceptions("Server error: $code")
    class RateLimitExceeded: AppExceptions("Rate limit")
    class UnknownException(): AppExceptions("Unknown error occurred")

}

fun Throwable.toAppException(): AppExceptions {
    return when(this) {
        is IOException -> AppExceptions.NetworkException()
        is HttpException -> {
            val code = code()
            when(code) {
                429 -> AppExceptions.RateLimitExceeded()
                else -> AppExceptions.ServerException(code)
            }
        }
        else -> AppExceptions.UnknownException()
    }
}