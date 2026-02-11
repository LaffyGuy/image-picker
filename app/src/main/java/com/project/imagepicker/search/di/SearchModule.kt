package com.project.imagepicker.search.di

import com.project.imagepicker.search.data.repositories.SearchImagesRepositoryImpl
import com.project.imagepicker.search.domain.SearchImagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SearchModule {

    @Binds
    fun bindSearchRepository(
        impl: SearchImagesRepositoryImpl
    ): SearchImagesRepository

}