package com.project.imagepicker.search.data.repositories

import com.project.imagepicker.core.common.LoadResult
import com.project.imagepicker.core.common.asResult
import com.project.imagepicker.core.network.api.ImageApi
import com.project.imagepicker.core.common.toImage
import com.project.imagepicker.search.domain.SearchImagesRepository
import com.project.imagepicker.core.common.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class SearchImagesRepositoryImpl @Inject constructor(private val imageApi: ImageApi): SearchImagesRepository {

    override fun getSearchImages(searchQuery: String): Flow<LoadResult<List<Image>>> = flow {
        emit(imageApi.searchImages(searchQuery).hits.map { it.toImage() })
    }
        .flowOn(Dispatchers.IO)
        .asResult()


}