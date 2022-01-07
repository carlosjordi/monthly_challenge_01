package com.carlosjordi.monthlychallenge01.presentation.game

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlosjordi.monthlychallenge01.domain.model.GameBoard
import com.carlosjordi.monthlychallenge01.domain.model.PlayerColor
import com.carlosjordi.monthlychallenge01.presentation.game.components.GameButton
import com.carlosjordi.monthlychallenge01.presentation.game.components.GameSlot
import com.carlosjordi.monthlychallenge01.presentation.game.components.ScoreboardSection
import com.carlosjordi.monthlychallenge01.presentation.game.components.TurnSection
import com.carlosjordi.monthlychallenge01.ui.theme.MonthlyChallenge01Theme
import com.carlosjordi.monthlychallenge01.util.slotColor

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primaryVariant)
            .padding(16.dp)
    ) {
        val state = gameViewModel.state.value
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            ScoreboardSection(
                modifier = Modifier.weight(2f),
                redScore = state.score[PlayerColor.RED] ?: 0,
                yellowScore = state.score[PlayerColor.YELLOW] ?: 0
            )
            TurnSection(
                modifier = Modifier.weight(2f),
                playerColor = state.currentPlayer
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(14f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (column in GameBoard.HORIZONTAL_RANGE) {
                    Column(
                        modifier = Modifier.clickable {
                            gameViewModel.onEvent(GameEvent.ClickColumn(column - 1))
                        },
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        for (row in GameBoard.VERTICAL_RANGE) {
                            val color = state.slots[column - 1][row - 1]
                            GameSlot(
                                modifier = Modifier.size(40.dp),
                                color = slotColor(playerColor = color)
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .weight(2f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GameButton(text = "Reiniciar Partida") {
                    gameViewModel.onEvent(GameEvent.RestartGame)
                }
                GameButton(text = "Reiniciar Puntaje") {
                    gameViewModel.onEvent(GameEvent.RestartScore)
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