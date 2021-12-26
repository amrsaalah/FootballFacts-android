package com.football.facts.network.response
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class LeaguesResponse(
    @Json(name = "errors") val errors: List<Any>,
    @Json(name = "get") val `get`: String,
    @Json(name = "paging") val paging: PagingResponse,
    @Json(name = "parameters") val parameters: ParametersResponse,
    @Json(name = "response") val response: List<LeagueDataResponse>,
    @Json(name = "results") val results: Int
) {

    @JsonClass(generateAdapter = true)
    data class ParametersResponse(
        @Json(name = "code") val code: String
    )

    @JsonClass(generateAdapter = true)
    data class LeagueDataResponse(
        @Json(name = "country") val country: CountryResponse,
        @Json(name = "league") val league: LeagueResponse,
        @Json(name = "seasons") val seasons: List<SeasonResponse>
    ) {
        @JsonClass(generateAdapter = true)
        data class CountryResponse(
            @Json(name = "code") val code: String,
            @Json(name = "flag") val flag: String,
            @Json(name = "name") val name: String
        )

        @JsonClass(generateAdapter = true)
        data class LeagueResponse(
            @Json(name = "id") val id: String,
            @Json(name = "logo") val logo: String,
            @Json(name = "name") val name: String,
            @Json(name = "type") val type: String
        )

        @JsonClass(generateAdapter = true)
        data class SeasonResponse(
            @Json(name = "coverage") val coverage: CoverageResponse,
            @Json(name = "current") val current: Boolean,
            @Json(name = "end") val end: String,
            @Json(name = "start") val start: String,
            @Json(name = "year") val year: Int
        ) {
            @JsonClass(generateAdapter = true)
            data class CoverageResponse(
                @Json(name = "fixtures") val fixtures: FixturesResponse,
                @Json(name = "injuries") val injuries: Boolean,
                @Json(name = "odds") val odds: Boolean,
                @Json(name = "players") val players: Boolean,
                @Json(name = "predictions") val predictions: Boolean,
                @Json(name = "standings") val standings: Boolean,
                @Json(name = "top_assists") val topAssists: Boolean,
                @Json(name = "top_cards") val topCards: Boolean,
                @Json(name = "top_scorers") val topScorers: Boolean
            ) {
                @JsonClass(generateAdapter = true)
                data class FixturesResponse(
                    @Json(name = "events") val events: Boolean,
                    @Json(name = "lineups") val lineups: Boolean,
                    @Json(name = "statistics_fixtures") val statisticsFixtures: Boolean,
                    @Json(name = "statistics_players") val statisticsPlayers: Boolean
                )
            }
        }
    }
}