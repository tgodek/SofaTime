package com.mangatalabs.sofatime

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.ImageLoader
import com.mangatalabs.sofatime.navigation.Screen
import com.mangatalabs.sofatime.navigation.bottomNavigation.BottomBar
import com.mangatalabs.sofatime.util.addHomeRoute
import com.mangatalabs.sofatime.util.addSearchRoute
import com.mangatalabs.sofatime.util.addSettingsRoute
import com.mangatalabs.sofatime.util.addTvDetailsRoute
import com.mangatalabs.sofatime.util.addTvTrackedRoute

@Composable
fun ActivityScaffold(
    navController: NavHostController,
    imageLoader: ImageLoader
) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val snackbarHostState = remember { SnackbarHostState() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        Screen.Home.route, Screen.TvSearch.route, Screen.TvTracked.route -> {
            bottomBarState.value = true
        }

        else -> bottomBarState.value = false
    }

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                content = {
                    BottomBar(
                        navController = navController
                    )
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            addHomeRoute(
                imageLoader = imageLoader,
                navController = navController
            )
            addTvDetailsRoute(
                imageLoader = imageLoader,
                snackbarHostState = snackbarHostState,
                navController = navController,
            )
            addSearchRoute(
                imageLoader = imageLoader,
                navController = navController
            )
            addTvTrackedRoute(
                imageLoader = imageLoader,
                navController = navController
            )
            addSettingsRoute(
                navController = navController
            )
        }
    }
}