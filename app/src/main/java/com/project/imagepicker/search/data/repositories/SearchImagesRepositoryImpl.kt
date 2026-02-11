package com.project.imagepicker.search.data.repositories

import com.project.imagepicker.core.common.LoadResult
import com.project.imagepicker.core.common.asResult
import com.project.imagepicker.core.network.api.ImageApi
import com.project.imagepicker.search.data.mappers.toImage
import com.project.imagepicker.search.domain.SearchImagesRepository
import com.project.imagepicker.search.domain.model.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchImagesRepositoryImpl @Inject constructor(private val pixabayApi: ImageApi): SearchImagesRepository {

    override fun getSearchImages(searchQuery: String): Flow<LoadResult<List<Image>>> = flow {
        emit(pixabayApi.searchImages(searchQuery).hits.map { it.toImage() })
    }
        .flowOn(Dispatchers.IO)
        .asResult()

}