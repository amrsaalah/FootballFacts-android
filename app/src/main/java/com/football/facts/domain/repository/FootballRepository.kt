package com.football.facts.domain.repository

import com.football.facts.data.source.FavoriteLocalDataSource
import com.football.facts.data.table.FavoriteTable
import com.football.facts.domain.entity.Country
import com.football.facts.domain.entity.League
import com.football.facts.domain.entity.Team
import com.football.facts.domain.valueObject.ELeagueType
import com.football.facts.domain.valueObject.FootballStatus
import com.football.facts.network.source.FootballNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FootballRepository @Inject constructor(
    private val footballNetwork: FootballNetworkDataSource,
    private val favoriteLocal: FavoriteLocalDataSource
) {

    suspend fun getStatus(): FootballStatus {
        val statusResponse = footballNetwork.getStatus()
        return FootballStatus(
            currentRequestsNumber = statusResponse.response.requests.current,
            limitRequestsNumberPerDay = statusResponse.response.requests.limitDay
        )
    }

    suspend fun getCountries(): List<Country> {
        val response = footballNetwork.getCountries()
        return response.response.map {
            Country(
                name = it.name,
                code = it.code,
                flagIcon = it.flag
            )
        }
    }

    suspend fun getLeaguesByCountryCode(countryCode: String?): List<League> {
        return withContext(Dispatchers.IO) {
            val leaguesDeferred = async { footballNetwork.getLeagues(countryCode) }
            val favoritesDeferred = async { favoriteLocal.getAllFavorites() }

            val favorites = favoritesDeferred.await()
            leaguesDeferred.await().response.map { leagueResponse ->
                val league = leagueResponse.league
                League(
                    id = league.id,
                    name = league.name,
                    type = ELeagueType.fromType(league.type),
                    logoIcon = league.logo,
                    isFavorite = favorites.any { it.leagueId == league.id }
                )
            }
        }
    }

    suspend fun updateLeagueFavorite(league: League, isFavorite: Boolean) {
        if (isFavorite) {
            favoriteLocal.insertFavorite(FavoriteTable(leagueId = league.id))
        } else {
            favoriteLocal.deleteFavoriteByLeagueId(league.id)
        }
    }

    suspend fun searchTeams(querySearch: String): List<Team> {
        val response = footballNetwork.searchTeams(querySearch)
        return response.response.map { data ->
            val team = data.team
            Team(
                id = team.id,
                name = team.name,
                logoIcon = team.logo,
                foundedYear = team.founded
            )
        }
    }
}