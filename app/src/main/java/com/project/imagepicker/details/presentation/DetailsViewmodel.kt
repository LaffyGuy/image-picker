package com.project.imagepicker.details.presentation

import androidx.lifecycle.ViewModel
import com.project.imagepicker.core.navigation.DetailsRoute
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel(assistedFactory = DetailsViewmodel.Factory::class)
class DetailsViewmodel @AssistedInject constructor(
     @Assisted navKey: DetailsRoute
): ViewModel() {

    private val imageId = MutableStateFlow(navKey.id)



    @AssistedFactory
    interface Factory {
        fun create(navKey: DetailsRoute): DetailsViewmodel
    }


}