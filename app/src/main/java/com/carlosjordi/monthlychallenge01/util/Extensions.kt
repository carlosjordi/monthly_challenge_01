package com.carlosjordi.monthlychallenge01.util

import com.carlosjordi.monthlychallenge01.domain.model.PlayerColor

/**
 * Returns true if the slot was marked by the current player.
 *
 * False in case there are no empty slots in the selected column.
 */
fun Array<Array<PlayerColor>>.markSlot(
    columnIndex: Int,
    playerColor: PlayerColor
): Boolean {
    val index = this[columnIndex].lastIndexOf(PlayerColor.NO_COLOR)
    if (index == -1) return false
    this[columnIndex][index] = playerColor
    return true
}