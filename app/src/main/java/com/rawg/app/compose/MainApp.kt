package com.rawg.app.compose

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.rawg.app.R
import com.rawg.app.stateholder.MainAppUiState
import com.rawg.app.stateholder.rememberMainAppUiState
import com.rawg.presentation.navigation.homeRoute
import com.rawg.ui.annotation.MultiPreviews
import com.rawg.ui.theme.RAWGTheme
import kotlinx.coroutines.delay

@Composable
fun MainApp(appState: MainAppUiState = rememberMainAppUiState()) {
    val context = LocalContext.current

    LaunchedEffect(key1 = appState.overrideBackPressed) {
        if (!appState.overrideBackPressed) {
            delay(2000)
            appState.overrideBackPressed = true
        }
    }

    if (appState.currentDestination.isTopLevelDestinationInHierarchy(homeRoute)) {
        BackHandler(appState.overrideBackPressed) {
            appState.overrideBackPressed = false
            Toast.makeText(context, R.string.press_again_to_exit, Toast.LENGTH_SHORT).show()
        }
    }
    RAWGTheme {
        MainAppNavHost(appState.navController)
    }
}

@MultiPreviews
@Composable
private fun MainAppPreview() {
    RAWGTheme {
        MainApp()
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(route: String) =
    this?.hierarchy?.any {
        it.route?.contains(route, true) ?: false
    } ?: false