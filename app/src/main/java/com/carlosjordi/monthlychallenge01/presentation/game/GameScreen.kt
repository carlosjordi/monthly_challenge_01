package com.carlosjordi.monthlychallenge01.presentation.game

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosjordi.monthlychallenge01.presentation.game.components.GameSlot
import com.carlosjordi.monthlychallenge01.ui.theme.MonthlyChallenge01Theme

@Composable
fun GameScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (column in 1..7) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (each in 1..6) {
                        GameSlot(
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Game Screen Light Theme")
@Preview(name = "Game Screen Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrevGameSlot() {
    MonthlyChallenge01Theme {
        GameScreen()
    }
}