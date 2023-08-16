package com.rawg.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rawg.presentation.page.GameListPage

const val gameListRoute = "gameList"

fun NavController.navigateToGameList(navOptions: NavOptions? = null) {
    this.navigate(gameListRoute, navOptions)
}

fun NavGraphBuilder.gameList() {
    composable(gameListRoute) {
        GameListPage()
    }
}