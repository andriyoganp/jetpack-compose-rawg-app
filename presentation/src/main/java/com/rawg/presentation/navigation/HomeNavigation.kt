package com.rawg.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rawg.presentation.page.HomePage

val homeRoute = "home"

fun NavGraphBuilder.home() {
    composable(homeRoute) {
        HomePage()
    }
}