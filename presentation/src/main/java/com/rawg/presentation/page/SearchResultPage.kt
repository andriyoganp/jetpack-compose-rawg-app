package com.rawg.presentation.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rawg.core.Resource
import com.rawg.model.Game
import com.rawg.presentation.component.GameGridItem
import com.rawg.presentation.component.GameShimmerGridItem
import com.rawg.presentation.component.GameShimmerItem
import com.rawg.presentation.navigation.navigateToGameDetail
import com.rawg.presentation.navigation.parentNavHostController
import com.rawg.presentation.viewmodel.SearchResultViewModel
import com.rawg.ui.component.AnimatedShimmer
import com.rawg.ui.theme.RAWGTheme

@Composable
fun SearchResultPage(viewModel: SearchResultViewModel = hiltViewModel()) {
    val gameListState by viewModel.gameListUiState.collectAsStateWithLifecycle()
    val gameList by viewModel.gameList.collectAsStateWithLifecycle()
    val search = viewModel.search
    val parentNavController = parentNavHostController.current
    SearchResultPageContent(gameListState, gameList, search, onBackPressed = {
        parentNavController.popBackStack()
    }, onGameItemPressed = {
        parentNavController.navigateToGameDetail(it.slug)
    }, onLoadMore = {
        viewModel.getGamesUseCase(true)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchResultPageContent(
    state: Resource<List<Game>>,
    gameList: List<Game> = listOf(),
    search: String = "",
    onGameItemPressed: (Game) -> Unit = {},
    onLoadMore: () -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    val scrollState = rememberLazyGridState()
    LaunchedEffect(scrollState.canScrollForward) {
        if (!scrollState.canScrollForward) {
            onLoadMore()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar({
                Text(
                    "Search Result",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            },
                Modifier
                    .shadow(1.dp)
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)),
                {
                    IconButton(onBackPressed) {
                        Icon(Icons.Default.ArrowBack, "back")
                    }
                })
        },
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(
                "Result of $search",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    start = 16.dp, end = 16.dp, top = 16.dp,
                )
            )
            Box(modifier = Modifier.weight(1F)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    state = scrollState,
                    contentPadding = PaddingValues(
                        bottom = 24.dp,
                        top = 12.dp,
                        start = 16.dp,
                        end = 16.dp,
                    )
                ) {
                    items(gameList.size) { index ->
                        GameGridItem(gameList[index]) { game ->
                            onGameItemPressed(game)
                        }
                    }
                    if (state is Resource.Loading) {
                        items(8) {
                            AnimatedShimmer { brush -> GameShimmerGridItem(brush) }
                        }
                    } else if (state is Resource.Failure) {
                        item("Error", { GridItemSpan(2) }) {
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
}

@Preview
@Composable
private fun SearchResultPagePreview() {
    RAWGTheme {
        SearchResultPageContent(Resource.Loading())
    }
}