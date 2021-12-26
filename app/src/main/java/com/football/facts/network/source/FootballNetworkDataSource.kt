package com.football.facts.network.source

import com.football.facts.network.response.LeaguesResponse
import com.football.facts.network.service.FootballApiService
import javax.inject.Inject

class FootballNetworkDataSource @Inject constructor(
    private val service : FootballApiService
) {

    suspend fun getStatus() = service.getStatus()
    suspend fun getCountries() = service.getCountries()
    suspend fun getLeagues(countryCode : String?) : LeaguesResponse{
        return service.getLeagues(countryCode)
    }

    suspend fun searchTeams(querySearch : String) = service.searchTeams(querySearch)
}