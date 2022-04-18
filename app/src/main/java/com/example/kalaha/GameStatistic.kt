package com.example.kalaha

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity(tableName = "game_statistic_database")
data class GameStatistic (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "score")
    val score: String
    ){

    constructor(name: String, time: String, score:String) : this(0, name, time, score)
}

