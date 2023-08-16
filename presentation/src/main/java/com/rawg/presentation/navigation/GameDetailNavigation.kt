package com.rawg.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rawg.presentation.page.GameDetailPage

const val slugArgument = "slug"
const val gameDetailRoute = "game-detail?slug={$slugArgument}"

fun NavController.navigateToGameDetail(slug: String, navOptions: NavOptions? = null) {
    this.navigate(
        gameDetailRoute.replace(oldValue = "{$slugArgument}", newValue = slug),
        navOptions,
    )
}

fun NavGraphBuilder.gameDetail() {
    composable(route = gameDetailRoute, arguments = listOf(navArgument(slugArgument) {
        type = NavType.StringType
    })) {
        GameDetailPage()
    }
}