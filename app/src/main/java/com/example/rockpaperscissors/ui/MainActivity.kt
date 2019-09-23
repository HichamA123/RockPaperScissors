package com.example.rockpaperscissors.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameRepository
import com.example.rockpaperscissors.model.Game
import com.example.rockpaperscissors.model.GameResult
import com.example.rockpaperscissors.model.Move

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

private lateinit var gameRepository: GameRepository

private val mainScope = CoroutineScope(Dispatchers.Main)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        gameRepository = GameRepository(this)
        initViews()
    }

    private fun initViews() {
        btnRock.setOnClickListener{view -> buildGame(Move.ROCK) }
        btnPaper.setOnClickListener{view -> buildGame(Move.PAPER) }
        btnScissors.setOnClickListener{view -> buildGame(Move.SCISSORS) }
    }

    private fun buildGame(userMove: Move) {
        val computerMove = getComputerMove()
        val gameResult: GameResult = calcGameResult(userMove, computerMove)

        mainScope.launch {
            val game = Game(getDate(), computerMove, userMove, gameResult)
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
            previewGame(game)
        }
    }

    private fun previewGame(game: Game) {
        tvGameResult.text = getString(game.result.stringId)
        tvComputerMove.foreground = getResources().getDrawable(game.computerMove.image)
        tvUserMove.foreground = getResources().getDrawable(game.userMove.image)
    }

    private fun getDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        return current.format(formatter)
    }

    private fun getComputerMove(): Move {
        return when((1..3).shuffled().first()) {
            1 -> Move.ROCK
            2 -> Move.PAPER
            3 -> Move.SCISSORS
            else -> Move.ROCK
        }
    }

    private fun calcGameResult(userMove: Move, computerMove: Move): GameResult {
        if((userMove == Move.PAPER && computerMove == Move.SCISSORS)
            || (userMove == Move.ROCK && computerMove == Move.PAPER)
            || (userMove == Move.SCISSORS && computerMove == Move.ROCK)) {
            return GameResult.LOSE
        } else if((computerMove == Move.PAPER && userMove == Move.SCISSORS)
            || (computerMove == Move.ROCK && userMove == Move.PAPER)
            || (computerMove == Move.SCISSORS && userMove == Move.ROCK)) {
            return GameResult.WIN
        } else {
            return GameResult.DRAW
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
