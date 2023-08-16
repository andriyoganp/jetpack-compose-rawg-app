package com.rawg.presentation.stateholder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.rawg.presentation.navigation.navigateToGameList
import com.rawg.presentation.navigation.navigateToFavouriteGame

@Stable
class HomeUiState(val navController: NavHostController) {
    private val navOptions
        get() = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }

    fun navigateToGameList() {
        navController.navigateToGameList(navOptions)
    }

    fun navigateToFavouriteGame() {
        navController.navigateToFavouriteGame(navOptions)
    }
}

@Composable
fun rememberHomeUiState(
    navController: NavHostController = rememberNavController(),
): HomeUiState {
    return remember(navController) { HomeUiState(navController) }
}