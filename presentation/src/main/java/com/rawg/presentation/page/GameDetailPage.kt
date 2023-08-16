package com.rawg.presentation.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.rawg.core.Resource
import com.rawg.model.Game
import com.rawg.presentation.R
import com.rawg.presentation.navigation.parentNavHostController
import com.rawg.presentation.viewmodel.GameDetailViewModel
import com.rawg.ui.annotation.MultiPreviews
import com.rawg.ui.component.AnimatedShimmer
import com.rawg.ui.theme.RAWGTheme

@Composable
fun GameDetailPage(viewModel: GameDetailViewModel = hiltViewModel()) {
    val parentNavController = parentNavHostController.current
    val gameDetailState by viewModel.gameDetailState.collectAsStateWithLifecycle()
    val isFavourite by viewModel.isFavourite.collectAsStateWithLifecycle()

    GameDetailPageContent(gameDetailState, isFavourite, onFavouritePressed = {
        if (isFavourite) {
            viewModel.deleteFromFavourite()
        } else {
            viewModel.addToFavourite()
        }
    }, onBackPressed = {
        parentNavController.popBackStack()
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GameDetailPageContent(
    state: Resource<Game> = Resource.Idle(),
    isFavourite: Boolean = false,
    onFavouritePressed: () -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    stringResource(R.string.game_detail),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            },
                modifier = Modifier
                    .shadow(1.dp)
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)),
                navigationIcon = {
                    IconButton(onBackPressed) {
                        Icon(Icons.Default.ArrowBack, "back")
                    }
                },
                actions = {
                    IconButton(onClick = { onFavouritePressed() }) {
                        Icon(
                            if (isFavourite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            "",
                            tint = if (isFavourite) Color.Red else Color.LightGray
                        )
                    }
                })
        },
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    when (state) {
                        is Resource.Loading -> {
                            AnimatedShimmer { brush ->
                                Spacer(
                                    modifier = Modifier
                                        .background(brush)
                                        .fillMaxSize()
                                )
                            }
                        }

                        is Resource.Success -> {
                            AsyncImage(
                                model = state.data.backgroundImage,
//                                placeholder = painterResource(
//                                    R.drawable.baseline_image_24
//                                ),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        else -> {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text("Can't load game", modifier = Modifier.align(Alignment.Center))
                            }
                        }
                    }
                }
            }
            if (state is Resource.Success) {
                item {
                    Text(
                        state.data.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
                    )
                    Text(
                        state.data.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@MultiPreviews
@Composable
private fun GameDetailPagePreview() {
    RAWGTheme {
        GameDetailPageContent(
            state = Resource.Success(
                Game(
                    "", "Description", "Game Name", 4.2, 5, "", "Slug",
                )
            )
        )
    }
}