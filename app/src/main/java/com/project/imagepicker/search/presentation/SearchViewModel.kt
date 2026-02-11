package com.project.imagepicker.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.imagepicker.core.common.LoadResult
import com.project.imagepicker.search.domain.model.Image
import com.project.imagepicker.search.domain.usecases.GetSearchImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchImagesUseCase: GetSearchImagesUseCase): ViewModel() {

    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState: StateFlow<SearchUiState> = _searchUiState

    fun searchImage(searchQuery: String) {
        viewModelScope.launch {
            getSearchImagesUseCase(searchQuery).collect { result ->
                when(result) {
                    LoadResult.Loading -> _searchUiState.update { it.copy(loadResult = LoadResult.Loading) }
                    is LoadResult.Success -> _searchUiState.update { it.copy(loadResult = LoadResult.Success(result.data)) }
                    is LoadResult.Error -> _searchUiState.update { it.copy(loadResult = LoadResult.Error(result.exception)) }
                }
            }
        }
    }


//    fun searchImage(searchQuery: String) {
//        Timber.d("AAAA Searching for: $searchQuery")
//        viewModelScope.launch {
//            getSearchImagesUseCase(searchQuery).collect { loadResult ->
//                Timber.d("AAAA LoadResult: $loadResult")
//                when(loadResult) {
//                    LoadResult.Loading -> {
//                        _searchUiState.update { it.copy(isLoading = true) }
//                    }
//
//                    is LoadResult.Success -> {
//                        _searchUiState.update { it.copy(isLoading = false, images = loadResult.data) }
//                    }
//
//                    is LoadResult.Error -> {
//                        _searchUiState.update {
//                            it.copy(isLoading = false,
//                                errorMessage = loadResult.exception.message ?: "Unknown message")
//                        }
//                    }
//                }
//            }
//        }
//    }

    fun onQueryChanged(newQuery: String) {
        _searchUiState.update { it.copy(searchQuery = newQuery) }
    }

    fun clearSearchQuery() {
        _searchUiState.update { it.copy(searchQuery = "") }
    }

}

data class SearchUiState(
    val searchQuery: String = "",
    val loadResult: LoadResult<List<Image>> = LoadResult.Success(emptyList())
//    val isLoading: Boolean = false,
//    val errorMessage: String = "",
//    val searchQuery: String = "",
//    val images: List<Image> = emptyList()
)