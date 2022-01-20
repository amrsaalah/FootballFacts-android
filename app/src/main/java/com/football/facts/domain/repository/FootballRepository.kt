package com.football.facts.domain.repository

import com.football.facts.data.source.FavoriteLocalDataSource
import com.football.facts.data.table.FavoriteTable
import com.football.facts.domain.entity.Country
import com.football.facts.domain.entity.Favorite
import com.football.facts.domain.entity.League
import com.football.facts.domain.entity.Team
import com.football.facts.domain.valueObject.EFavoriteType.LEAGUE
import com.football.facts.domain.valueObject.EFavoriteType.TEAM
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
            val favoritesDeferred = async { favoriteLocal.getFavoriteLeagues() }

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


    suspend fun updateFavorite(favorite: Favorite , isFavorite: Boolean){
        if(isFavorite){
            val favoriteTable = FavoriteTable(
                leagueId = when(favorite.type){
                    LEAGUE -> favorite.id
                    TEAM -> null
                },
                icon = favorite.icon,
                name = favorite.name,
                teamId = when(favorite.type){
                    LEAGUE -> null
                    TEAM -> favorite.id
                }
            )
            favoriteLocal.insertFavorite(favoriteTable)
        }else{
            when(favorite.type){
                LEAGUE -> {
                    favoriteLocal.deleteFavoriteByLeagueId(favorite.id)
                }
                TEAM -> {
                    favoriteLocal.deleteFavoriteByTeamId(favorite.id)
                }
            }
        }
    }

    suspend fun searchTeams(querySearch: String): List<Team> {
        return withContext(Dispatchers.IO) {
            val teamsDeferred = async { footballNetwork.searchTeams(querySearch) }
            val favoritesDeferred = async { favoriteLocal.getFavoriteTeams() }
            val favorites = favoritesDeferred.await()
            teamsDeferred.await().response.map { teamResponse ->
                val team = teamResponse.team
                Team(
                    id = team.id,
                    name = team.name,
                    logoIcon = team.logo,
                    isFavorite = favorites.any { it.teamId == team.id },
                    foundedYear = team.founded
                )
            }
        }
    }

    suspend fun getFavoriteTeams() : List<Favorite>{
        return favoriteLocal.getFavoriteTeams().map { team->
            Favorite(
                id = team.teamId!!,
                name = team.name,
                icon = team.icon ,
                type = TEAM
            )
        }
    }


    suspend fun getFavoriteLeagues() : List<Favorite>{
        return favoriteLocal.getFavoriteLeagues().map { league->
            Favorite(
                id = league.leagueId!!,
                name = league.name,
                icon = league.icon ,
                type = LEAGUE
            )
        }
    }
}