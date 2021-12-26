package com.football.facts.data.source

import com.football.facts.data.dao.FavoriteDao
import com.football.facts.data.table.FavoriteTable
import javax.inject.Inject

class FavoriteLocalDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) {
    suspend fun getAllFavorites() : List<FavoriteTable>{
        return favoriteDao.getAllFavorites()
    }

    suspend fun insertFavorite(favoriteTable: FavoriteTable) = favoriteDao.insert(favoriteTable)
    suspend fun deleteFavoriteByLeagueId(leagueId : String) = favoriteDao.deleteByLeagueId(leagueId)
}