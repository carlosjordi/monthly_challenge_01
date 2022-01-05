package com.carlosjordi.monthlychallenge01.domain.model

enum class PlayerColor {
    NO_COLOR,
    RED,
    YELLOW;

    companion object {
        fun changeCurrentPlayerColor(playerColor: PlayerColor): PlayerColor = when (playerColor) {
            RED -> YELLOW
            YELLOW -> RED
            NO_COLOR -> NO_COLOR
        }
    }

}