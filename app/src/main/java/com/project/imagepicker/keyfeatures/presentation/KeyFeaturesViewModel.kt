package com.project.imagepicker.keyfeatures.presentation

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.project.imagepicker.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class KeyFeaturesViewModel @Inject constructor() : ViewModel() {

    private val _pagerCount = MutableStateFlow(0)
    val pagerCount: StateFlow<Int> = _pagerCount


    private val _uiState = MutableStateFlow(
        KeyFeaturesUiState(
            keyFeatures = listOf(
                R.drawable.key_feature_1,
                R.drawable.key_feature_2
            )
        )
    )

    val uiState: StateFlow<KeyFeaturesUiState> = _uiState


    fun changePageCount(page: Int) {
        _pagerCount.value = page
    }

}

data class KeyFeaturesUiState(
    @DrawableRes val keyFeatures: List<Int> = emptyList(),
)