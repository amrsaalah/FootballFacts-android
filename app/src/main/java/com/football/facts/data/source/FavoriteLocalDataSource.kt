package com.football.facts.data.source

import com.football.facts.data.dao.FavoriteDao
import com.football.facts.data.table.FavoriteTable
import javax.inject.Inject

class FavoriteLocalDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) {

    suspend fun insertFavorite(favoriteTable: FavoriteTable) = favoriteDao.insert(favoriteTable)
    suspend fun deleteFavoriteByLeagueId(leagueId : String) = favoriteDao.deleteByLeagueId(leagueId)
    suspend fun deleteFavoriteByTeamId(teamId : String) = favoriteDao.deleteByTeamId(teamId)

    suspend fun getFavoriteLeagues() = favoriteDao.getFavoriteLeagues()
    suspend fun getFavoriteTeams() = favoriteDao.getFavoriteTeams()
}