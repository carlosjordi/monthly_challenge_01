package com.carlosjordi.monthlychallenge01.presentation.game.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlosjordi.monthlychallenge01.domain.model.PlayerColor
import com.carlosjordi.monthlychallenge01.ui.theme.MonthlyChallenge01Theme
import com.carlosjordi.monthlychallenge01.util.slotColor

@Composable
fun TurnSection(
    modifier: Modifier = Modifier,
    playerColor: PlayerColor,
    indicatorSize: Dp = 20.dp
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Turno",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = slotColor(playerColor)
        )
        Spacer(modifier = Modifier.width(8.dp))
        GameSlot(
            modifier = Modifier.size(indicatorSize),
            color = slotColor(playerColor)
        )
    }
}

@Preview(name = "Turn Section Light Theme")
@Preview(name = "Turn Section Dark Theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PrevTurnSection() {
    MonthlyChallenge01Theme {
        TurnSection(playerColor = PlayerColor.RED)
    }
}