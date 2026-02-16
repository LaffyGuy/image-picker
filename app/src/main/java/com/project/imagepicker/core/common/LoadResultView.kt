package com.project.imagepicker.core.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.imagepicker.core.exceptions.AppExceptions

@Composable
fun <T> LoadResultView(
    loadResult: LoadResult<T>,
    content: @Composable (T) -> Unit,
    tryAgainAction: () -> Unit,
    exceptionToMessageMapper: ExceptionToMessageMapper = ExceptionToMessageMapper,
    modifier: Modifier = Modifier
) {

    when(loadResult) {
        LoadResult.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
        }
        is LoadResult.Success -> {
            content(loadResult.data)
        }
        is LoadResult.Error -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val exception = loadResult.exception as? AppExceptions
                    ?: AppExceptions.UnknownException()

                Text(
                    text = exceptionToMessageMapper.getLocalizedMessage(exception),
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = tryAgainAction
                ) {
                    Text(
                        text = "Try Again"
                    )
                }
            }
        }
    }

}