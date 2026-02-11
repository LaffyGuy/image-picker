package com.project.imagepicker.search.data.mappers

import com.project.imagepicker.core.network.dto.NetworkImage
import com.project.imagepicker.search.domain.model.Image

fun NetworkImage.toImage(): Image {
    return Image(
        id = id.toLong(),
        imageURL = imageURL,
        type = type,
        tags = tags,
        likes = likes,
        user = user
    )
}