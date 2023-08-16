package com.rawg.presentation.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.rawg.presentation.navigation.navigateSearchResult
import com.rawg.presentation.navigation.parentNavHostController
import com.rawg.ui.annotation.MultiPreviews
import com.rawg.ui.theme.RAWGTheme

@Composable
fun SearchPage() {
    val parentNavController = parentNavHostController.current
    SearchPageContent(onBackPressed = {
        parentNavController.popBackStack()
    }, onSearch = {
        parentNavController.navigateSearchResult(it)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchPageContent(onBackPressed: () -> Unit = {}, onSearch: (String) -> Unit = {}) {
    var search by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar({
                Text(
                    "Input Search",
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
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            TextField(
                value = search, modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    search = it
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { onSearch(search) }),
                trailingIcon = {
                    IconButton(onClick = { onSearch(search) }) {
                        Icon(Icons.Filled.Search, "")
                    }
                }
            )
        }
    }
}


@MultiPreviews
@Composable
private fun SearchPagePreview() {
    RAWGTheme {
        SearchPageContent()
    }
}