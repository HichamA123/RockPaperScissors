package com.example.rockpaperscissors.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "game_table")
data class Game(
    @ColumnInfo
    var date: String,

    @ColumnInfo
    var computerMove: Move,

    @ColumnInfo
    var userMove: Move,

    @ColumnInfo
    var result: GameResult,

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
): Parcelable