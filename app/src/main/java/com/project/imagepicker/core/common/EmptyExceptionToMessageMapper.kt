package com.project.imagepicker.core.common

import com.project.imagepicker.core.exceptions.AppExceptions

class EmptyExceptionToMessageMapper: ExceptionToMessageMapper {

    override fun getLocalizedMessage(exception: AppExceptions): String {
        return exception.message ?: "Ann error occurred"
    }
}