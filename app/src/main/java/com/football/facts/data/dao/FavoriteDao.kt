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

    @Query("DELETE from favorites where team_id = :teamId")
    suspend fun deleteByTeamId(teamId : String)

    @Query("SELECT * FROM favorites where league_id IS NOT NULL")
    suspend fun getFavoriteLeagues() : List<FavoriteTable>

    @Query("SELECT * FROM favorites where team_id IS NOT NULL")
    suspend fun getFavoriteTeams() : List<FavoriteTable>
}