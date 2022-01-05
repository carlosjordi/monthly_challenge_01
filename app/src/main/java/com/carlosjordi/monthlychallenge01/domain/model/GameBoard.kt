package com.carlosjordi.monthlychallenge01.domain.model

object GameBoard {

    private const val HORIZONTAL_SIZE = 7
    private const val VERTICAL_SIZE = 6

    val HORIZONTAL_RANGE = 1..HORIZONTAL_SIZE
    val VERTICAL_RANGE = 1..VERTICAL_SIZE

    val slots = Array(HORIZONTAL_SIZE) { Array(VERTICAL_SIZE) { PlayerColor.NO_COLOR } }

}