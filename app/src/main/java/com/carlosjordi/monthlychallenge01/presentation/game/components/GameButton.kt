package com.carlosjordi.monthlychallenge01.presentation.game.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.carlosjordi.monthlychallenge01.ui.theme.MonthlyChallenge01Theme

@Composable
fun GameButton(
    text: String,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

@Preview(name = "Game Button Light Theme")
@Preview(name = "Game Button Dark Theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PrevGameButton() {
    MonthlyChallenge01Theme {
        GameButton(
            text = "Reiniciar Partida",
            onClick = {}
        )
    }
}