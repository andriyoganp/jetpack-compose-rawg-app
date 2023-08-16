package com.rawg.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rawg.presentation.page.SearchResultPage

const val searchArgument = "search"
const val searchResultRoute = "search-result?search={$searchArgument}"

fun NavController.navigateSearchResult(search: String, navOptions: NavOptions? = null) {
    this.navigate(
        searchResultRoute.replace(oldValue = "{$searchArgument}", newValue = search),
        navOptions
    )
}

fun NavGraphBuilder.searchResult() {
    composable(
        searchResultRoute,
        arguments = listOf(navArgument(searchArgument) { type = NavType.StringType })
    ) {
        SearchResultPage()
    }
}