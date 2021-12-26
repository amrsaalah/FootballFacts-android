package com.football.facts.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.football.facts.data.table.FavoriteTable

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteTable>

    @Insert
    suspend fun insertAll(favorites: List<FavoriteTable>)

    @Insert
    suspend fun insert(favorite : FavoriteTable)

    @Delete
    suspend fun delete(favorite: FavoriteTable)

    @Query("DELETE from favorites where league_id = :leagueId")
    suspend fun deleteByLeagueId(leagueId : String)
}