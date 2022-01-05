package com.carlosjordi.monthlychallenge01.presentation.game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.carlosjordi.monthlychallenge01.domain.model.PlayerColor
import com.carlosjordi.monthlychallenge01.util.markSlot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(GameState())
    val state: State<GameState>
        get() = _state

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.ClickColumn -> {
                markSlot(event.column)
            }
            GameEvent.RestartGame -> {

            }
            GameEvent.RestartScore -> {

            }
        }
    }

    private fun markSlot(columnIndex: Int) {
        if (_state.value.slots.markSlot(columnIndex, state.value.currentPlayer)) {
            _state.value = state.value.copy(
                currentPlayer = PlayerColor.changeCurrentPlayerColor(state.value.currentPlayer)
            )
        }
    }
}