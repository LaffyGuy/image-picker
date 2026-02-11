package com.project.imagepicker.core.network.dto

data class NetworkResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<NetworkImage>
)