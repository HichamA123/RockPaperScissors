package com.example.rockpaperscissors.model

import com.example.rockpaperscissors.R

enum class GameResult(var stringId: Int) {
    WIN(R.string.user_wins), LOSE(R.string.computer_wins), DRAW(R.string.draw)
}