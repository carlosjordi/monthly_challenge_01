package com.carlosjordi.monthlychallenge01.presentation.game

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlosjordi.monthlychallenge01.R
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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ScoreboardSection(
                redScore = state.score[PlayerColor.RED] ?: 0,
                yellowScore = state.score[PlayerColor.YELLOW] ?: 0
            )
            TurnSection(
                playerColor = state.currentPlayer
            )
            Image(
                painter = painterResource(id = R.drawable.monthly_challenge_logo),
                contentDescription = stringResource(R.string.cd_logo_image)
            )
            if (!gameViewModel.canPlay) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            gameViewModel.onEvent(GameEvent.RestartGame)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.cant_play_message),
                        fontSize = 24.sp,
                        color = MaterialTheme.colors.secondary,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                val deviceConfiguration = LocalConfiguration.current
                val slotSize = (deviceConfiguration.screenWidthDp - 46) / 7
                for (column in GameBoard.HORIZONTAL_RANGE) {
                    Column(
                        modifier = Modifier.clickable {
                            gameViewModel.onEvent(GameEvent.ClickColumn(column - 1))
                        }
                    ) {
                        for (row in GameBoard.VERTICAL_RANGE) {
                            val color = state.slots[column - 1][row - 1]
                            val animatedColor by animateColorAsState(
                                targetValue = slotColor(color),
                                animationSpec = tween(
                                    durationMillis = 500
                                )
                            )
                            GameSlot(
                                size = slotSize.dp,
                                color = animatedColor
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GameButton(text = stringResource(R.string.play_button)) {
                    gameViewModel.onEvent(GameEvent.RestartGame)
                }
                GameButton(text = stringResource(R.string.score_button)) {
                    gameViewModel.onEvent(GameEvent.RestartScore)
                }
            }
            gameViewModel.lastWinner?.let { winner ->
                WinnerDialog(
                    isOpen = state.winningTurn,
                    onDismissDialog = gameViewModel::onDismissDialog,
                    winner = winner,
                    playAgain = { gameViewModel.onEvent(GameEvent.RestartGame) }
                )
            }
        }
    }
}

@Preview(name = "Game Screen Light Theme")
@Preview(name = "Game Screen Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PrevGameSlot() {
    MonthlyChallenge01Theme {
        GameScreenNoVM()
    }
}

/**
 * Only used for the previewer
 */
@Composable
fun GameScreenNoVM() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primaryVariant)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ScoreboardSection(
                redScore = 1,
                yellowScore = 2
            )
            TurnSection(
                playerColor = PlayerColor.RED
            )
            Image(
                painter = painterResource(id = R.drawable.monthly_challenge_logo),
                contentDescription = "Logo"
            )
            // board
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                val deviceConfiguration = LocalConfiguration.current
                val slotSize = (deviceConfiguration.screenWidthDp - 46) / 7
                for (column in GameBoard.HORIZONTAL_RANGE) {
                    Column {
                        for (row in GameBoard.VERTICAL_RANGE) {
                            GameSlot(
                                size = slotSize.dp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
            // buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GameButton(text = "Jugar") {}
                GameButton(text = "Puntaje") {}
            }
        }
    }
}