package com.project.imagepicker.core.network.dto

import com.google.gson.annotations.SerializedName

data class NetworkImage(
    val id: Int,
    @SerializedName("webformatURL")
    val imageURL: String,
    val type: String,
    val tags: String,
    val likes: Int,
    val user: String
)


