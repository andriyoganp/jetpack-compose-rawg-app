package com.rawg.app.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rawg.presentation.navigation.gameDetail
import com.rawg.presentation.navigation.home
import com.rawg.presentation.navigation.homeRoute
import com.rawg.presentation.navigation.parentNavHostController
import com.rawg.presentation.navigation.search
import com.rawg.presentation.navigation.searchResult

@Composable
fun MainAppNavHost(
    navController: NavHostController,
    startDestination: String = homeRoute,
) {
    CompositionLocalProvider(
        parentNavHostController provides navController,
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
        ) {
            home()
            gameDetail()
            search()
            searchResult()
        }
    }
}