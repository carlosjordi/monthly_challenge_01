package com.carlosjordi.monthlychallenge01.presentation.game

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.carlosjordi.monthlychallenge01.domain.model.PlayerColor
import com.carlosjordi.monthlychallenge01.presentation.game.components.GameButton
import com.carlosjordi.monthlychallenge01.ui.theme.PlayerRed
import com.carlosjordi.monthlychallenge01.ui.theme.PlayerYellow

@Composable
fun WinnerDialog(
    modifier: Modifier = Modifier,
    isOpen: Boolean,
    onDismissDialog: () -> Unit,
    winner: PlayerColor,
    playAgain: () -> Unit
) {
    if (isOpen) {
        AlertDialog(
            onDismissRequest = onDismissDialog,
            confirmButton = {
                GameButton(text = "Volver a Jugar") {
                    playAgain()
                }
            },
            text = {
                val winnerPlayer = if (winner == PlayerColor.RED) "Rojo"
                else "Amarillo"
                Text(
                    text = "\nJugador $winnerPlayer gana!\n\uD83C\uDFC6\uD83C\uDFC6\uD83C\uDFC6",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = if (winner == PlayerColor.RED) PlayerRed else PlayerYellow
                )
            },
            modifier = modifier
                .fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.secondary
        )
    }
}