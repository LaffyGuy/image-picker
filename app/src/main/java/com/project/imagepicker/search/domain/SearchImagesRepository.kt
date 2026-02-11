package com.project.imagepicker.search.domain

import com.project.imagepicker.core.common.LoadResult
import com.project.imagepicker.search.domain.model.Image
import kotlinx.coroutines.flow.Flow

interface SearchImagesRepository {

    fun getSearchImages(searchQuery: String): Flow<LoadResult<List<Image>>>

}