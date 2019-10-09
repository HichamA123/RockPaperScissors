package com.example.rockpaperscissors.database

import android.content.Context
import androidx.room.*
import com.example.rockpaperscissors.model.Converters
import com.example.rockpaperscissors.model.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    //companion object just the same as static in Java (singleton)
    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        //volatile means that the changes that are made to this variable are immediately made visible to other threads
        @Volatile
        private var gameRoomDatabaseInstance: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (gameRoomDatabaseInstance == null) {
                synchronized(GameRoomDatabase::class.java) {
                    if (gameRoomDatabaseInstance == null) {
                        gameRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext,
                            GameRoomDatabase::class.java, DATABASE_NAME).build()
                    }
                }

            }
            return gameRoomDatabaseInstance
        }
    }
}