package com.mangatalabs.sofatime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import org.koin.android.ext.android.inject
import com.mangatalabs.core_ui.theme.SofaTimeTheme as Theme

class MainActivity : ComponentActivity() {

    private val imageLoader by inject<ImageLoader>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                val navController = rememberNavController()
                ActivityScaffold(
                    navController = navController,
                    imageLoader = imageLoader
                )
            }
        }
    }
}