package com.carlosjordi.monthlychallenge01.util

import androidx.compose.ui.graphics.Color
import com.carlosjordi.monthlychallenge01.domain.model.PlayerColor
import com.carlosjordi.monthlychallenge01.ui.theme.PlayerRed
import com.carlosjordi.monthlychallenge01.ui.theme.PlayerYellow

/**
 * Parses the [PlayerColor] to the [Color] that will
 * be printed on the slot
 */
fun slotColor(playerColor: PlayerColor): Color = when (playerColor) {
    PlayerColor.NO_COLOR -> Color.White
    PlayerColor.RED -> PlayerRed
    PlayerColor.YELLOW -> PlayerYellow
}