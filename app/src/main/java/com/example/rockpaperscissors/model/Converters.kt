package com.example.rockpaperscissors.model

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromIntToGameResult(value: Int?): GameResult? {
        return when(value) {
            -1 -> GameResult.LOSE
            0 -> GameResult.DRAW
            1 -> GameResult.WIN
            else -> null
        }
    }

    @TypeConverter
    fun GameResultToInt(gameResult: GameResult?): Int? {
        return when(gameResult) {
            GameResult.LOSE -> -1
            GameResult.DRAW -> 0
            GameResult.WIN -> 1
            else -> null
        }
    }

    @TypeConverter
    fun fromIntToMove(value: Int?): Move? {
        return when(value) {
            -1 -> Move.ROCK
            0 -> Move.SCISSORS
            1 -> Move.PAPER
            else -> null
        }
    }

    @TypeConverter
    fun MoveToInt(move: Move?): Int? {
        return when(move) {
            Move.ROCK -> -1
            Move.SCISSORS -> 0
            Move.PAPER -> 1
            else -> null
        }
    }
}