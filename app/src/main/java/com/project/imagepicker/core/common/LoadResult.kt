package com.project.imagepicker.core.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class LoadResult<out T> {

    data object Loading: LoadResult<Nothing>()

    data class Success<T>(val data: T): LoadResult<T>()

    data class Error(val exception: Exception): LoadResult<Nothing>()

}

fun <T> Flow<T>.asResult(): Flow<LoadResult<T>> =
    map<T, LoadResult<T>> { LoadResult.Success(it) }
    .onStart { emit(LoadResult.Loading) }
    .catch { emit(LoadResult.Error(it as Exception)) }
