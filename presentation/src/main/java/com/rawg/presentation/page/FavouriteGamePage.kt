package com.rawg.presentation.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rawg.core.Resource
import com.rawg.model.Game
import com.rawg.presentation.R
import com.rawg.presentation.component.GameItem
import com.rawg.presentation.component.GameShimmerItem
import com.rawg.presentation.navigation.navigateToGameDetail
import com.rawg.presentation.navigation.parentNavHostController
import com.rawg.presentation.viewmodel.FavouriteGameViewModel
import com.rawg.ui.annotation.MultiPreviews
import com.rawg.ui.component.AnimatedShimmer
import com.rawg.ui.theme.RAWGTheme

@Composable
fun FavouriteGamePage(viewModel: FavouriteGameViewModel = hiltViewModel()) {
    val parentNavHostController = parentNavHostController.current
    val state by viewModel.favouriteGamesState.collectAsStateWithLifecycle()
    FavouriteGamePageContent(state, onGameItemPressed = {
        parentNavHostController.navigateToGameDetail(it.slug)
    })
}

@Composable
private fun FavouriteGamePageContent(
    state: Resource<List<Game>> = Resource.Idle(),
    onGameItemPressed: (Game) -> Unit = {},
) {
    Column {
        Text(
            stringResource(R.string.favourite_games),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        if (state is Resource.Success && state.data.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    stringResource(R.string.favourite_game_is_empty), modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1F),
            contentPadding = PaddingValues(
                bottom = 24.dp,
                top = 12.dp,
                start = 16.dp,
                end = 16.dp,
            )
        ) {
            if (state is Resource.Success) {
                items(state.data.size) { index ->
                    GameItem(state.data[index]) {
                        onGameItemPressed(it)
                    }
                }
            }
            if (state is Resource.Loading) {
                items(4) {
                    AnimatedShimmer { GameShimmerItem(it) }
                }
            } else if (state is Resource.Failure) {
                item("Error", { GridItemSpan(2) }) {
                    Text(
                        state.errorMessage.orEmpty(),
                        modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp)
                    )
                }
            }
        }
    }
}

@MultiPreviews
@Composable
private fun FavouriteGamePagePreview() {
    RAWGTheme {
        FavouriteGamePageContent(Resource.Loading())
    }
}