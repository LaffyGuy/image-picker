package com.project.imagepicker.core.network

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class NetworkConfig(
    val baseUrl: String = "https://pixabay.com/",
    val timeout: Duration = 10.seconds,
    val isDebug: Boolean = true
)
