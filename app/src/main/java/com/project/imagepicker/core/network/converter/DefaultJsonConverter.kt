package com.project.imagepicker.core.network.converter

import kotlinx.serialization.json.Json

fun createDefaultJson(
    isDebug: Boolean
): Json {
    return Json {
        if (isDebug) {
            prettyPrint = true
        }
    }
}