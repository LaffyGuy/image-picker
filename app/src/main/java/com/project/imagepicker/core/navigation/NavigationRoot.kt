package com.project.imagepicker.core.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.project.imagepicker.details.presentation.DetailsScreen
import com.project.imagepicker.details.presentation.DetailsViewmodel
import com.project.imagepicker.search.presentation.SearchScreen
import com.project.imagepicker.search.presentation.SearchViewModel

@Composable
fun NavigationRoot(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(SearchRoute())

    Scaffold(
          modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        NavDisplay(
            backStack = backStack,
            modifier = modifier.padding(paddingValues),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<SearchRoute> { key ->
                    SearchScreen(
                        tagQuery = key.query,
                        clickToImageDetails = { id ->
                            backStack.add(DetailsRoute(id))
                        }
                    )
                }
                entry<DetailsRoute> { key ->
                    val viewModel = hiltViewModel<DetailsViewmodel, DetailsViewmodel.Factory>(
                        creationCallback = { factory ->
                            factory.create(key)
                        }
                    )
                    DetailsScreen(
                        onClickToTag = { tag ->
                            backStack.add(SearchRoute(tag))
                        },
                        viewmodel = viewModel
                    )
                }
            }
        )
    }

}