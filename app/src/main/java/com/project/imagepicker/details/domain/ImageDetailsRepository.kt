package com.project.imagepicker.details.domain

import com.project.imagepicker.core.common.Image

interface ImageDetailsRepository {

    suspend fun getImageDetails(id: Long): Image

}