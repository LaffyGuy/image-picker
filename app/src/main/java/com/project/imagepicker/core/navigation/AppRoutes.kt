package com.project.imagepicker.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object SearchRoute: NavKey

@Serializable
data class DetailsRoute(
    val id: Long
): NavKey