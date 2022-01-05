package com.carlosjordi.monthlychallenge01.presentation.game

import androidx.lifecycle.ViewModel
import com.carlosjordi.monthlychallenge01.domain.model.GameBoard
import com.carlosjordi.monthlychallenge01.domain.model.PlayerColor
import com.carlosjordi.monthlychallenge01.util.markSlot
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    private var currentPlayerColor = PlayerColor.RED

    fun markSlot(columnIndex: Int) {
        if (GameBoard.slots.markSlot(columnIndex, currentPlayerColor)) {
            currentPlayerColor = PlayerColor.changeCurrentPlayerColor(currentPlayerColor)
        }
    }
}