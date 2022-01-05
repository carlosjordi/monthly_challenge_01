package com.carlosjordi.monthlychallenge01.presentation.game

import com.carlosjordi.monthlychallenge01.domain.model.GameBoard
import com.carlosjordi.monthlychallenge01.domain.model.PlayerColor

data class GameState(
    val slots: Array<Array<PlayerColor>> = GameBoard.slots,
    var currentPlayer: PlayerColor = PlayerColor.RED,
    val score: Map<PlayerColor, Int> = mapOf(
        PlayerColor.RED to 0,
        PlayerColor.YELLOW to 0
    )
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameState

        if (!slots.contentDeepEquals(other.slots)) return false
        if (currentPlayer != other.currentPlayer) return false
        if (score != other.score) return false

        return true
    }

    override fun hashCode(): Int {
        var result = slots.contentDeepHashCode()
        result = 31 * result + currentPlayer.hashCode()
        result = 31 * result + score.hashCode()
        return result
    }
}
