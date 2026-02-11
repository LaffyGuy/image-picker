package com.project.imagepicker.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute

@Serializable
data class DetailsRoute(
    val id: Int
)