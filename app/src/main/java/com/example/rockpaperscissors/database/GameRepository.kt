package com.example.rockpaperscissors.database

import android.content.Context
import com.example.rockpaperscissors.model.Game

class GameRepository(context: Context) {
    private var gameDao: GameDao

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase!!.gameDao()
    }

    suspend fun getAllGames(): List<Game> = gameDao.getAllGames()

    suspend fun insertGame(game: Game) = gameDao.insertGame(game)

//    suspend fun deleteProduct(product: Product) = gameDao.deleteProduct(game)

    suspend fun deleteAllGames() = gameDao.deleteAllGames()

}