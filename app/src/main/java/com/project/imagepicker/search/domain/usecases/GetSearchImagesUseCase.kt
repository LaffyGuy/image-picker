package com.project.imagepicker.search.domain.usecases

import com.project.imagepicker.core.common.LoadResult
import com.project.imagepicker.search.domain.SearchImagesRepository
import com.project.imagepicker.core.common.Image
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchImagesUseCase @Inject constructor(private val searchImagesRepository: SearchImagesRepository) {

    operator fun invoke(searchQuery: String): Flow<LoadResult<List<Image>>> {
        return searchImagesRepository.getSearchImages(searchQuery)
    }

}