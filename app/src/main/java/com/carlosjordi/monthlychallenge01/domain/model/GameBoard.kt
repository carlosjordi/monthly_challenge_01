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
                return checkHorizontalVictory(redSlots) ||
                        checkVerticalVictory(redSlots) ||
                        checkDiagonalLeftToRightVictory(redSlots) ||
                        checkDiagonalRightToLeftVictory(redSlots)
            }
            PlayerColor.YELLOW -> {
                if (yellowSlots.size < MINIMUM_SLOTS_TO_WIN) return false
                yellowSlots.sort()
                return checkHorizontalVictory(yellowSlots) ||
                        checkVerticalVictory(yellowSlots) ||
                        checkDiagonalLeftToRightVictory(yellowSlots) ||
                        checkDiagonalRightToLeftVictory(yellowSlots)
            }
        }
    }

    fun resetBoardGame() {
        redSlots.clear()
        yellowSlots.clear()
        for (column in HORIZONTAL_RANGE) {
            for (row in VERTICAL_RANGE) {
                slots[column - 1][row - 1] = PlayerColor.NO_COLOR
            }
        }
    }

    /**
     * Groups slots by its second digit.
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

    /**
     * Groups slots by its first digit.
     *
     * Ej. If we had 14 15 24 33 35 42 44 45 55
     *
     * It'd be grouped this way
     *
     * 14,15 group '1'
     *
     * 24 group '2'
     *
     * 33,35 group '3'
     *
     * 42,44,45 group '4'
     *
     * 55 group '5'
     */
    private fun checkVerticalVictory(markedSlots: List<String>): Boolean {
        val validGroups = markedSlots.groupBy { it.first() }.filter { group ->
            group.value.size >= MINIMUM_SLOTS_TO_WIN
        }
        if (validGroups.isEmpty()) return false
        // at this point, valid groups only contain groups of 4 or more
        validGroups.forEach { group ->
            val secondDigits = group.value.map { it.last().toString().toInt() }
            var successfullyConsecutiveCounter = 0
            for ((index, digit) in secondDigits.withIndex()) {
                // 3 successful cycles mean the win condition was met
                if (successfullyConsecutiveCounter == 3) return true
                //if we reach the last digit with no win met, it means
                // we can't win
                if (index == secondDigits.lastIndex) return false
                // if one on the digits is not consecutive, we reset
                // the counter
                if (digit + 1 != secondDigits[index + 1]) {
                    successfullyConsecutiveCounter = 0
                } else
                    successfullyConsecutiveCounter++
            }
        }
        return false
    }

    /**
     * We first filter the slots that are in a zone where a diagonal
     * from right to left can be made which in this case is from
     *
     * first digit has to be between 0-3
     *
     * second digit has to be between 0-2
     *
     * Ej. If we had 14 15 24 33 35 42 44 45 55
     *
     * The valid slots are:
     *
     * none
     */
    private fun checkDiagonalLeftToRightVictory(markedSlots: List<String>): Boolean {
        val validSlots = markedSlots.filter { slot ->
            slot.first().toString().toInt() <= HORIZONTAL_SIZE - MINIMUM_SLOTS_TO_WIN &&
                    slot.last().toString().toInt() <= VERTICAL_SIZE - MINIMUM_SLOTS_TO_WIN
        }
        if (validSlots.isEmpty()) return false

        validSlots.forEach { slot ->
            var firstDigit = slot.first().toString().toInt()
            var secondDigit = slot.last().toString().toInt()
            var successfullyConsecutiveCounter = 0
            for (i in 1..3) {
                firstDigit++
                secondDigit++
                val nextInDiagonal = "$firstDigit$secondDigit"
                // we look up in the original list
                val found = markedSlots.find { it == nextInDiagonal }
                if (found != null) successfullyConsecutiveCounter++
                else break
            }
            if (successfullyConsecutiveCounter == 3) return true
        }
        return false
    }

    /**
     * We first filter the slots that are in a zone where a diagonal
     * from left to right can be made which in this case is from
     *
     * first digit has to be between 3-6
     *
     * second digit has to be between 0-2
     *
     * Ej. If we had 14 15 24 33 35 42 44 45 55
     *
     * The valid slots are:
     *
     * 42
     */
    private fun checkDiagonalRightToLeftVictory(markedSlots: List<String>): Boolean {
        val validSlots = markedSlots.filter { slot ->
            slot.first().toString().toInt() >= HORIZONTAL_SIZE - MINIMUM_SLOTS_TO_WIN &&
                    slot.last().toString().toInt() <= VERTICAL_SIZE - MINIMUM_SLOTS_TO_WIN
        }
        if (validSlots.isEmpty()) return false

        validSlots.forEach { slot ->
            var firstDigit = slot.first().toString().toInt()
            var secondDigit = slot.last().toString().toInt()
            var successfullyConsecutiveCounter = 0
            for (i in 1..3) {
                firstDigit--
                secondDigit++
                val nextInDiagonal = "$firstDigit$secondDigit"
                // we look up in the original list
                val found = markedSlots.find { it == nextInDiagonal }
                if (found != null) successfullyConsecutiveCounter++
                else break
            }
            if (successfullyConsecutiveCounter == 3) return true
        }
        return false
    }
}