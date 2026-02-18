package com.project.imagepicker.keyfeatures.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.project.imagepicker.R
import com.project.imagepicker.ui.theme.ImagePickerTheme

@Composable
fun KeyFeaturesScreen() {

    val viewModel: KeyFeaturesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerCount by viewModel.pagerCount.collectAsStateWithLifecycle()

    KeyFeaturesContent(
        keyFeatures = uiState.keyFeatures,
        currentPage = pagerCount,
        clickOnConfirmButton = {},
        onPageChanged = viewModel::changePageCount
    )

}

@Composable
fun KeyFeaturesContent(
    keyFeatures: List<Int>,
    currentPage: Int,
    clickOnConfirmButton: () -> Unit,
    onPageChanged: (Int) -> Unit
) {

    val pagerState = rememberPagerState(
        initialPage = currentPage,
        pageCount = {
            keyFeatures.size
        }
    )

    LaunchedEffect(pagerState.currentPage) {
        onPageChanged(pagerState.currentPage)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { page ->

            val feature = keyFeatures[page]

            AsyncImage(
                model = feature,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        if (pagerState.currentPage == keyFeatures.lastIndex) {
            Button(
                onClick = clickOnConfirmButton,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "Confirm"
                )
            }
        }

    }


}

@Preview(showSystemUi = true)
@Composable
private fun KeyFeaturesContentPreview() {
    ImagePickerTheme {
        KeyFeaturesContent(
            keyFeatures = listOf(
                R.drawable.key_feature_1,
                R.drawable.key_feature_2
            ),
            currentPage = 1,
            clickOnConfirmButton = {},
            onPageChanged = {}
        )
    }
}