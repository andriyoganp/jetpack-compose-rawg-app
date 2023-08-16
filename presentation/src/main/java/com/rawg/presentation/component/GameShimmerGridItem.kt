package com.rawg.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GameShimmerGridItem(brush: Brush) {
    Card {
        Column(
            modifier = Modifier
                .height(160.dp)
                .background(brush)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.7F)
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(
                        Color.Gray
                    )
            )
        }
    }
}