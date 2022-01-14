package com.carlosjordi.monthlychallenge01.presentation.game.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.carlosjordi.monthlychallenge01.ui.theme.MonthlyChallenge01Theme

@Composable
fun GameSlot(
    size: Dp = 40.dp,
    showBorder: Boolean = true,
    color: Color = MaterialTheme.colors.background
) {
    val borderColor = if (showBorder) MaterialTheme.colors.background
    else MaterialTheme.colors.primaryVariant

    Box(
        modifier = Modifier
            .size(size)
            .fillMaxSize()
            .border(
                width = 1.dp,
                color = borderColor
            )
            .padding(4.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(percent = 50)
            )

    )
}

@Preview(name = "Game Slot Light Theme")
@Preview(name = "Game Slot Dark Theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PrevGameSlot() {
    MonthlyChallenge01Theme {
        GameSlot()
    }
}