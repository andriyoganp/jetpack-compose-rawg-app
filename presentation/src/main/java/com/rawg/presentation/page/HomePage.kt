package com.rawg.presentation.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rawg.presentation.stateholder.HomeUiState
import com.rawg.presentation.stateholder.rememberHomeUiState
import com.rawg.ui.annotation.MultiPreviews
import com.rawg.ui.theme.RAWGTheme

@Composable
fun HomePage(state: HomeUiState = rememberHomeUiState()) {
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            HomeBottomBar(selectedIndex, onBottomItemPressed = {
                when (it) {
                    0 -> {
                        selectedIndex = 0
                        state.navigateToGameList()
                    }

                    1 -> {
                        selectedIndex = 1
                        state.navigateToFavouriteGame()
                    }
                }
            })
        }, content = {
            HomeNavHost(state.navController, Modifier.padding(it))
        })
}

@Composable
private fun HomeBottomBar(selectedIndex: Int, onBottomItemPressed: (Int) -> Unit = {}) {
    BottomAppBar(Modifier.height(60.dp)) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onBottomItemPressed(0) },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = if (selectedIndex == 0) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
            ) {
                Icon(
                    if (selectedIndex == 0) Icons.Filled.Home else Icons.Outlined.Home,
                    "Games"
                )
            }
            IconButton(
                onClick = { onBottomItemPressed(1) },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = if (selectedIndex == 1) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                )
            ) {
                Icon(
                    if (selectedIndex == 1) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    "Favourite"
                )
            }
        }
    }
}

@MultiPreviews
@Composable
private fun HomePagePagePreview() {
    RAWGTheme {
        HomePage()
    }
}