package com.rawg.app.stateholder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Stable
class MainAppUiState(val navController: NavHostController) {
    var overrideBackPressed by mutableStateOf(true)
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
}

@Composable
fun rememberMainAppUiState(
    navController: NavHostController = rememberNavController(),
): MainAppUiState = remember(navController) {
    MainAppUiState(navController)
}