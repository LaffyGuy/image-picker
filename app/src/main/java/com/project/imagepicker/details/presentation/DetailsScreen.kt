package com.project.imagepicker.details.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.project.imagepicker.ui.theme.ImagePickerTheme

@Composable
fun DetailsScreen(
    viewmodel: DetailsViewmodel
) {

    val details by viewmodel.detailsUiState.collectAsStateWithLifecycle()

    AsyncImage(
        model = details.details.imageURL,
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )

}

@Composable
fun DetailsContent() {

}

@Preview(showSystemUi = true)
@Composable
private fun DetailsContentPreview() {
    ImagePickerTheme {
        DetailsContent()
    }
}