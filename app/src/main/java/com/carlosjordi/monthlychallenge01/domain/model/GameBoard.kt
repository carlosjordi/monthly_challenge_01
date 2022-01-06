package com.carlosjordi.monthlychallenge01.domain.model

object GameBoard {

    private const val HORIZONTAL_SIZE = 7
    private const val VERTICAL_SIZE = 6
    private const val MINIMUM_SLOTS_TO_WIN = 4

    val HORIZONTAL_RANGE = 1..HORIZONTAL_SIZE
    val VERTICAL_RANGE = 1..VERTICAL_SIZE

    val slots = Array(HORIZONTAL_SIZE) { Array(VERTICAL_SIZE) { PlayerColor.NO_COLOR } }

    private val redSlots = mutableListOf<String>()
    private val yellowSlots = mutableListOf<String>()

    /**
     * Returns true if the slot was marked by the current player.
     *
     * False in case there are no empty slots in the selected column.
     */
    fun markSlot(
        columnIndex: Int,
        playerColor: PlayerColor
    ): Boolean {
        val index = slots[columnIndex].lastIndexOf(PlayerColor.NO_COLOR)
        if (index == -1) return false
        slots[columnIndex][index] = playerColor
        val currentSlot = "$columnIndex$index"
        addSlot(playerColor, currentSlot)
        return true
    }

    /**
     * Saves the coordinates for the player, so we can eventually
     * check if one of them has won
     */
    private fun addSlot(playerColor: PlayerColor, currentSlot: String) {
        when (playerColor) {
            PlayerColor.NO_COLOR -> {
            }
            PlayerColor.RED -> {
                redSlots.add(currentSlot)
            }
            PlayerColor.YELLOW -> {
                yellowSlots.add(currentSlot)
            }
        }
    }

    /**
     * Checks player victory
     */
    fun checkVictory(playerColor: PlayerColor): Boolean {
        when (playerColor) {
            PlayerColor.NO_COLOR -> {
                return false
            }
            PlayerColor.RED -> {
                if (redSlots.size < MINIMUM_SLOTS_TO_WIN) return false
                redSlots.sort()
                return checkHorizontalVictory(redSlots)
            }
            PlayerColor.YELLOW -> {
                if (yellowSlots.size < MINIMUM_SLOTS_TO_WIN) return false
                yellowSlots.sort()
                return checkHorizontalVictory(yellowSlots)
            }
        }
    }

    /**
     * Groups slots by his second digit.
     *
     * Ej. If we had 14 15 24 33 35 42 44 45 55
     *
     * It'd be grouped this way
     *
     * 14,24,44 group '4'
     *
     * 15,35,45,55 group '5'
     *
     * 33 group '3'
     *
     * 42 group '2'
     */
    private fun checkHorizontalVictory(markedSlots: List<String>): Boolean {
        val validGroups = markedSlots.groupBy { it.last() }.filter { group ->
            group.value.size >= MINIMUM_SLOTS_TO_WIN
        }
        if (validGroups.isEmpty()) return false
        // at this point, valid groups only contain groups of 4 or more
        validGroups.forEach { group ->
            val firstDigits = group.value.map { it.first().toString().toInt() }
            var successfullyConsecutiveCounter = 0
            for ((index, digit) in firstDigits.withIndex()) {
                // 3 successful cycles mean the win condition was met
                if (successfullyConsecutiveCounter == 3) return true
                //if we reach the last digit with no win met, it means
                // we can't win
                if (index == firstDigits.lastIndex) return false
                // if one on the digits is not consecutive, we reset
                // the counter
                if (digit + 1 != firstDigits[index + 1]) {
                    successfullyConsecutiveCounter = 0
                } else
                    successfullyConsecutiveCounter++
            }
        }
        return false
    }
}