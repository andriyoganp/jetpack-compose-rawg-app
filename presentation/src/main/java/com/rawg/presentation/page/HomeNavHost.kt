package com.rawg.presentation.page

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rawg.presentation.navigation.gameList
import com.rawg.presentation.navigation.gameListRoute
import com.rawg.presentation.navigation.favouriteGame
import com.rawg.presentation.navigation.search
import com.rawg.presentation.navigation.searchResult

@Composable
fun HomeNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navHostController, gameListRoute, modifier) {
        gameList()
        favouriteGame()
    }
}