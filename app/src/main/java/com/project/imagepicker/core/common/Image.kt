package com.project.imagepicker.core.common

data class Image(
    val id: Long,
    val imageURL: String,
    val type: String,
    val tags: String,
    val likes: Int,
    val user: String
)