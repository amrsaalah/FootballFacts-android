package com.football.facts.data.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorites"
)
data class FavoriteTable(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "league_id") val leagueId: String?,
    @ColumnInfo(name = "team_id") val teamId : String?,
    @ColumnInfo(name = "icon") val icon : String?,
    @ColumnInfo(name = "name") val name : String?
)