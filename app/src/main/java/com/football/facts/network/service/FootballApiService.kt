package com.football.facts.network.service

import com.football.facts.network.response.CountriesResponse
import com.football.facts.network.response.LeaguesResponse
import com.football.facts.network.response.StatusResponse
import com.football.facts.network.response.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiService {

    @GET("status")
    suspend fun getStatus() : StatusResponse

    @GET("countries")
    suspend fun getCountries() : CountriesResponse

    @GET("leagues")
    suspend fun getLeagues(@Query("code") countryCode : String?) : LeaguesResponse

    @GET("teams")
    suspend fun searchTeams(@Query("search") querySearch : String) : TeamsResponse
}