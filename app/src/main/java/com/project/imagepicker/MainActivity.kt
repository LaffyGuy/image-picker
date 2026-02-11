package com.project.imagepicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.project.imagepicker.core.navigation.NavigationRoot
import com.project.imagepicker.search.presentation.SearchScreen
import com.project.imagepicker.ui.theme.ImagePickerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImagePickerTheme {
                NavigationRoot()
            }
        }
    }
}
