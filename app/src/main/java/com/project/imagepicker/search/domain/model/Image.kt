package com.project.imagepicker.search.domain.model

data class Image(
    val id: Long,
    val imageURL: String,
    val type: String,
    val tags: String,
    val likes: Int,
    val user: String
)