package com.project.imagepicker.details.data.repositories

import com.project.imagepicker.core.common.Image
import com.project.imagepicker.core.common.toImage
import com.project.imagepicker.core.network.api.ImageApi
import com.project.imagepicker.details.domain.ImageDetailsRepository
import javax.inject.Inject

class ImageDetailsRepositoryImpl @Inject constructor(private val imageApi: ImageApi): ImageDetailsRepository {

    override suspend fun getImageDetails(id: Long): Image {
        return imageApi.getImageDetails(id.toString()).toImage()
    }
}