package com.project.imagepicker.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class SearchRoute(
    val query: String? = null
): NavKey

@Serializable
data class DetailsRoute(
    val id: Long
): NavKey