package com.project.imagepicker.search.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.imagepicker.core.common.LoadResult
import com.project.imagepicker.search.domain.model.Image
import com.project.imagepicker.search.presentation.components.EmptyContent
import com.project.imagepicker.search.presentation.components.ErrorContent
import com.project.imagepicker.search.presentation.components.ImageItem
import com.project.imagepicker.search.presentation.components.SearchView
import com.project.imagepicker.ui.theme.ImagePickerTheme

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = hiltViewModel()
    val searchUiState by viewModel.searchUiState.collectAsStateWithLifecycle()

    SearchContent(
        loadResult = searchUiState.loadResult,
        searchQuery = searchUiState.searchQuery,
        searchQueryChanged = viewModel::onQueryChanged,
        onSearch = viewModel::searchImage,
        onClearQuery = viewModel::clearSearchQuery
    )
}

@Composable
fun SearchContent(
    loadResult: LoadResult<List<Image>>,
    searchQuery: String,
    searchQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onClearQuery: () -> Unit
) {

//    LaunchedEffect(Unit) {
//        Timber.d( "AAAA data - $images")
//    }

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchView(
            query = searchQuery,
            onQueryChange = searchQueryChanged,
            onSearch = onSearch,
            onClearQuery = onClearQuery
        )
        when(loadResult) {
            LoadResult.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
            }
            is LoadResult.Success -> {
                if (loadResult.data.isEmpty()) {
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
                        items(loadResult.data) { image ->
                            ImageItem(
                                user = image.user,
                                likes = image.likes,
                                imageUrl = image.imageURL
                            )
                        }
                    }
                }

            }
            is LoadResult.Error -> {
                ErrorContent(
                    errorMessage = loadResult.exception.message ?: "Unknown error"
                )
            }
        }
//        if (images.isEmpty()) {
//            EmptyContent()
//        }
//        else {

        }
//        if (errorMessage != null) {
//            ErrorContent(
//                errorMessage = errorMessage
//            )
//        }
//    }

}

@Preview(showSystemUi = true)
@Composable
private fun SearchContentPreview() {
    ImagePickerTheme {
        SearchContent(
            loadResult = LoadResult.Loading,
            searchQuery = "",
            searchQueryChanged = {},
            onSearch = {},
            onClearQuery = {}
        )
    }
}