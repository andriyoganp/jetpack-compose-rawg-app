package com.rawg.presentation.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rawg.core.Resource
import com.rawg.model.Game
import com.rawg.presentation.R
import com.rawg.presentation.component.GameItem
import com.rawg.presentation.component.GameShimmerItem
import com.rawg.presentation.navigation.navigateToGameDetail
import com.rawg.presentation.navigation.navigateToSearch
import com.rawg.presentation.navigation.parentNavHostController
import com.rawg.presentation.viewmodel.GameListViewModel
import com.rawg.ui.annotation.MultiPreviews
import com.rawg.ui.component.AnimatedShimmer
import com.rawg.ui.theme.RAWGTheme

@Composable
fun GameListPage(viewModel: GameListViewModel = hiltViewModel()) {
    val gameListState by viewModel.gameListUiState.collectAsStateWithLifecycle()
    val gameList by viewModel.gameList.collectAsStateWithLifecycle()
    val parentNavController = parentNavHostController.current
    GameListPageContent(
        gameListState, gameList, onSearchPressed = {
            parentNavController.navigateToSearch()
        }, onGameItemPressed = {
            parentNavController.navigateToGameDetail(it.slug)
        }, onLoadMore = {
            viewModel.getGamesUseCase(nextPage = true)
        })
}

@Composable
private fun GameListPageContent(
    state: Resource<List<Game>>,
    gameList: List<Game> = listOf(),
    onSearchPressed: () -> Unit = {},
    onGameItemPressed: (Game) -> Unit = {},
    onLoadMore: () -> Unit = {},
) {
    val scrollState = rememberLazyListState()
    LaunchedEffect(scrollState.canScrollForward) {
        if (!scrollState.canScrollForward) {
            onLoadMore()
        }
    }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                stringResource(R.string.games),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1F)
            )
            IconButton(onClick = onSearchPressed) {
                Icon(Icons.Filled.Search, "", tint = MaterialTheme.colorScheme.primary)
            }
        }
        Box(modifier = Modifier.weight(1F)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                state = scrollState,
                contentPadding = PaddingValues(
                    bottom = 24.dp,
                    top = 12.dp,
                    start = 16.dp,
                    end = 16.dp,
                )
            ) {
                items(gameList.size) { index ->
                    GameItem(gameList[index]) {
                        onGameItemPressed(it)
                    }
                }
                if (state is Resource.Loading) {
                    items(8) {
                        AnimatedShimmer { GameShimmerItem(it) }
                    }
                } else if (state is Resource.Failure) {
                    item("Error") {
                        Text(
                            state.errorMessage.orEmpty(),
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@MultiPreviews
@Composable
private fun GameListPreview() {
    RAWGTheme {
        GameListPageContent(Resource.Loading())
    }
}