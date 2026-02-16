package com.project.imagepicker.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.imagepicker.core.common.Image
import com.project.imagepicker.core.navigation.DetailsRoute
import com.project.imagepicker.details.domain.usecases.GetImageDetailsByIdUseCase
import com.project.imagepicker.search.domain.SearchImagesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewmodel.Factory::class)
class DetailsViewmodel @AssistedInject constructor(
    private val detailsByIdUseCase: GetImageDetailsByIdUseCase,
     @Assisted navKey: DetailsRoute
): ViewModel() {

    private val imageId = MutableStateFlow(navKey.id)

    private val _detailsUiState = MutableStateFlow(DetailsUiState())
    val detailsUiState: StateFlow<DetailsUiState> = _detailsUiState


    init {
        getImageDetailsById()
    }

    private fun getImageDetailsById() {
        viewModelScope.launch {
            val details = detailsByIdUseCase(imageId.value)
            _detailsUiState.update { it.copy(
                details = details
            ) }
        }
    }



    @AssistedFactory
    interface Factory {
        fun create(navKey: DetailsRoute): DetailsViewmodel
    }


}

data class DetailsUiState(
    val details: Image = Image(
        id = 0,
        imageURL = "",
        type = "",
        tags = "",
        likes = 0,
        user = "",
    )
)