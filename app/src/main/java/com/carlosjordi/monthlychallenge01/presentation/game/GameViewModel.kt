package com.carlosjordi.monthlychallenge01.presentation.game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.carlosjordi.monthlychallenge01.domain.model.GameBoard
import com.carlosjordi.monthlychallenge01.domain.model.PlayerColor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(GameState())
    val state: State<GameState>
        get() = _state

    var lastWinner: PlayerColor? = null
        private set

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.ClickColumn -> {
                val currentPlayer = state.value.currentPlayer
                markSlot(event.column)
                if (GameBoard.checkVictory(currentPlayer)) {
                    victoryAchieved(currentPlayer)
                }
            }
            GameEvent.RestartGame -> {
                GameBoard.resetBoardGame()
                val startingPlayer =
                    PlayerColor.changeCurrentPlayerColor(state.value.startingPlayer)
                _state.value = state.value.copy(
                    startingPlayer = startingPlayer,
                    currentPlayer = startingPlayer,
                    winningTurn = false
                )
            }
            GameEvent.RestartScore -> {
                val score = mapOf(PlayerColor.RED to 0, PlayerColor.YELLOW to 0)
                _state.value = state.value.copy(
                    score = score.toMutableMap()
                )
            }
        }
    }

    fun onDismissDialog() {
        _state.value = state.value.copy(
            winningTurn = false
        )
    }

    private fun markSlot(columnIndex: Int) {
        if (GameBoard.markSlot(columnIndex, state.value.currentPlayer)) {
            _state.value = state.value.copy(
                currentPlayer = PlayerColor.changeCurrentPlayerColor(state.value.currentPlayer)
            )
        }
    }

    private fun victoryAchieved(currentPlayerColor: PlayerColor) {
        GameBoard.resetBoardGame()

        when (currentPlayerColor) {
            PlayerColor.NO_COLOR -> {
            }
            PlayerColor.RED -> {
                _state.value.score[PlayerColor.RED] = state.value.score[PlayerColor.RED]!! + 1
            }
            PlayerColor.YELLOW -> {
                _state.value.score[PlayerColor.YELLOW] = state.value.score[PlayerColor.YELLOW]!! + 1
            }
        }
        val startingPlayer = PlayerColor.changeCurrentPlayerColor(state.value.startingPlayer)
        lastWinner = currentPlayerColor
        _state.value = state.value.copy(
            startingPlayer = startingPlayer,
            currentPlayer = startingPlayer,
            winningTurn = true
        )
    }
}