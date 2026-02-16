package com.project.imagepicker.core.di

import com.project.imagepicker.core.common.DefaultExceptionToMessageMapper
import com.project.imagepicker.core.common.ExceptionToMessageMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindExceptionToMessageMapper(
        impl: DefaultExceptionToMessageMapper
    ): ExceptionToMessageMapper

}