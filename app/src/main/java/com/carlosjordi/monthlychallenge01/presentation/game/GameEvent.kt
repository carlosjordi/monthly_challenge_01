package com.carlosjordi.monthlychallenge01.presentation.game

sealed class GameEvent {
    data class ClickColumn(val column: Int) : GameEvent()
    object RestartGame : GameEvent()
    object RestartScore : GameEvent()
}
