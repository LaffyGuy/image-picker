package com.project.imagepicker.core.common

import com.project.imagepicker.core.exceptions.AppExceptions

interface ExceptionToMessageMapper {

    fun getLocalizedMessage(exception: AppExceptions): String

    companion object : ExceptionToMessageMapper {

        private var instance: ExceptionToMessageMapper = EmptyExceptionToMessageMapper()

        override fun getLocalizedMessage(exception: AppExceptions): String {
            return instance.getLocalizedMessage(exception)
        }

        fun set(mapper: ExceptionToMessageMapper) {
            this.instance = mapper
        }

        fun reset() {
            instance = EmptyExceptionToMessageMapper()
        }

    }

}