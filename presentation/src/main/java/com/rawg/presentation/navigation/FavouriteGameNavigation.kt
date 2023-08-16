package com.rawg.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rawg.presentation.page.FavouriteGamePage

val favouriteGameRoute = "favourite-game"

fun NavController.navigateToFavouriteGame(navOptions: NavOptions? = null) {
    this.navigate(favouriteGameRoute, navOptions)
}

fun NavGraphBuilder.favouriteGame() {
    composable(favouriteGameRoute) {
        FavouriteGamePage()
    }
}