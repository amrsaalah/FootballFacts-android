package com.football.facts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.football.facts.data.dao.FavoriteDao
import com.football.facts.data.table.FavoriteTable

@Database(entities = [FavoriteTable::class], version = 1)
abstract class FootballDatabase : RoomDatabase() {

    companion object{
        const val DATABASE_NAME = "football_db"
    }

    abstract fun favoriteDao(): FavoriteDao
}