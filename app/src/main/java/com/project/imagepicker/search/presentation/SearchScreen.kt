package com.project.imagepicker.search.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.imagepicker.core.common.ExceptionToMessageMapper
import com.project.imagepicker.core.common.LoadResult
import com.project.imagepicker.core.common.Image
import com.project.imagepicker.core.common.LoadResultView
import com.project.imagepicker.search.presentation.components.EmptyContent
import com.project.imagepicker.search.presentation.components.ErrorContent
import com.project.imagepicker.search.presentation.components.ImageItem
import com.project.imagepicker.search.presentation.components.SearchView
import com.project.imagepicker.ui.theme.ImagePickerTheme

@Composable
fun SearchScreen(
    tagQuery: String?,
    clickToImageDetails: (Long) -> Unit,
) {
    val viewModel: SearchViewModel = hiltViewModel()
    val searchUiState by viewModel.searchUiState.collectAsStateWithLifecycle()


    LaunchedEffect(tagQuery) {
        tagQuery?.let { viewModel.handleInitialQuery(it) }
    }

    LoadResultView(
        loadResult = searchUiState.loadResult,
        tryAgainAction = {},
        content = { imagesList ->
            SearchContent(
                searchUiState = searchUiState,
                imagesList = imagesList,
                onQueryChange = viewModel::onQueryChanged,
                onSearch = viewModel::searchImage,
                onClearQuery = viewModel::clearSearchQuery,
                onClickToDetails = clickToImageDetails
            )
        }
    )
}

@Composable
fun SearchContent(
    searchUiState: SearchUiState,
    imagesList: List<Image>,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onClearQuery: () -> Unit,
    onClickToDetails: (Long) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchView(
            query = searchUiState.searchQuery,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            onClearQuery = onClearQuery
        )

        if (imagesList.isEmpty()) {
            EmptyContent()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(imagesList) { image ->
                    ImageItem(
                        user = image.user,
                        likes = image.likes,
                        imageUrl = image.imageURL,
                        modifier = Modifier.clickable {
                            onClickToDetails(image.id)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SearchContentPreview() {
    ImagePickerTheme {
        SearchContent(
            searchUiState = SearchUiState(),
            imagesList = emptyList(),
            onQueryChange = {},
            onSearch = {},
            onClearQuery = {},
            onClickToDetails = {}
        )
    }
}