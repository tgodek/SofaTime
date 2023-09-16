package com.mangatalabs.sofatime.navigation.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.mangatalabs.sofatime.R
import com.mangatalabs.sofatime.navigation.Route

sealed class BottomNavItem(val title: Int, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem(R.string.title_home, Icons.Default.Home, Route.HOME)
    object Profile : BottomNavItem(R.string.title_profile, Icons.Default.Person, Route.TV_TRACKED)
    object Search : BottomNavItem(R.string.title_search, Icons.Default.Search, Route.TV_SEARCH)

    companion object {
        val bottomNavItems = listOf(Home, Search , Profile)
    }
}