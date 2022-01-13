package com.carlosjordi.monthlychallenge01.presentation.game.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosjordi.monthlychallenge01.ui.theme.MonthlyChallenge01Theme
import com.carlosjordi.monthlychallenge01.ui.theme.PlayerRed
import com.carlosjordi.monthlychallenge01.ui.theme.PlayerYellow

@Composable
fun ScoreboardSection(
    modifier: Modifier = Modifier,
    redScore: Int,
    yellowScore: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Scoreboard(
            text = "Rojo",
            score = redScore,
            color = PlayerRed
        )
        Scoreboard(
            text = "Amarillo",
            score = yellowScore,
            color = PlayerYellow
        )
    }
}

@Composable
private fun Scoreboard(
    text: String,
    score: Int,
    color: Color
) {
    Text(
        text = "$text $score",
        color = color,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview(name = "Scoreboard Section Light Theme")
@Preview(name = "Scoreboard Section Dark Theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PrevScoreboardSection() {
    MonthlyChallenge01Theme {
        ScoreboardSection(
            redScore = 2,
            yellowScore = 5
        )
    }
}