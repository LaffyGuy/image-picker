package com.project.imagepicker.core.common

import android.content.Context
import com.project.imagepicker.R
import com.project.imagepicker.core.exceptions.AppExceptions
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultExceptionToMessageMapper @Inject constructor(
    @ApplicationContext private val context: Context
) : ExceptionToMessageMapper {

    override fun getLocalizedMessage(exception: AppExceptions): String {
        return when (exception) {
            is AppExceptions.NetworkException -> context.getString(R.string.network_error_message)
            is AppExceptions.ServerException -> context.getString(R.string.server_error_message, exception.code)
            is AppExceptions.RateLimitExceeded -> context.getString(R.string.rate_limit_error_message)
            is AppExceptions.UnknownException -> context.getString(R.string.unknown_error_message)
        }
    }
}