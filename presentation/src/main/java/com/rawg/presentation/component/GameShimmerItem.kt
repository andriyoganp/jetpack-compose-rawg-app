package com.rawg.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun GameShimmerItem(brush: Brush) {
    Row {
        Card(
            modifier = Modifier
                .weight(0.4F),
            shape = MaterialTheme.shapes.small
        ) {
            Column(
                modifier = Modifier
                    .height(100.dp)
                    .background(brush)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(0.6F)
                .padding(horizontal = 8.dp),
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(
                        brush, shape = MaterialTheme.shapes.extraSmall
                    )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(14.dp)
                    .background(brush, shape = MaterialTheme.shapes.extraSmall)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(12.dp)
                    .background(brush, shape = MaterialTheme.shapes.extraSmall)
            )
        }
    }
}