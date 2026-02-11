package com.project.imagepicker.details.domain.usecases

import com.project.imagepicker.core.common.Image
import com.project.imagepicker.details.domain.ImageDetailsRepository
import javax.inject.Inject

class GetImageDetailsByIdUseCase @Inject constructor(private val imageDetailsRepository: ImageDetailsRepository) {

    suspend operator fun invoke(id: Long): Image {
        return imageDetailsRepository.getImageDetails(id)
    }

}