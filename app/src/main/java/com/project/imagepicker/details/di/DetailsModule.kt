package com.project.imagepicker.details.di

import com.project.imagepicker.details.data.repositories.ImageDetailsRepositoryImpl
import com.project.imagepicker.details.domain.ImageDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DetailsModule {

    @Binds
    fun bindImageDetailsRepository(
        impl: ImageDetailsRepositoryImpl
    ): ImageDetailsRepository


}