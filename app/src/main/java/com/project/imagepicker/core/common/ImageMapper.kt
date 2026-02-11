package com.project.imagepicker.core.common

import com.project.imagepicker.core.network.dto.NetworkImage

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