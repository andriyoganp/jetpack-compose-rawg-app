package com.rawg.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rawg.app.compose.MainApp
import com.rawg.ui.theme.RAWGTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RAWGTheme {
                // A surface container using the 'background' color from the theme
                MainApp()
            }
        }
    }
}