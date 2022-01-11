package com.carlosjordi.monthlychallenge01.presentation.game

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.carlosjordi.monthlychallenge01.domain.model.PlayerColor
import com.carlosjordi.monthlychallenge01.presentation.game.components.GameButton

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
                    onDismissDialog()
                }
            },
            text = {
                val winnerPlayer = if (winner == PlayerColor.RED) "Rojo"
                else "Amarillo"
                Text(text = "Jugador $winnerPlayer gana! \uD83C\uDFC6\uD83C\uDFC6")
            },
            modifier = modifier.fillMaxWidth()
        )
    }
}