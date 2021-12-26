package com.football.facts.network.response

import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class TeamsResponse(
    @Json(name = "errors") val errors: List<Any>,
    @Json(name = "get") val `get`: String,
    @Json(name = "paging") val paging: PagingResponse,
    @Json(name = "parameters") val parameters: ParametersResponse,
    @Json(name = "response") val response: List<TeamDataResponse>,
    @Json(name = "results") val results: Int
) {

    @JsonClass(generateAdapter = true)
    data class ParametersResponse(
        @Json(name = "search") val search: String
    )

    @JsonClass(generateAdapter = true)
    data class TeamDataResponse(
        @Json(name = "team") val team: TeamResponse,
        @Json(name = "venue") val venue: VenueResponse
    ) {
        @JsonClass(generateAdapter = true)
        data class TeamResponse(
            @Json(name = "country") val country: String,
            @Json(name = "founded") val founded: String?,
            @Json(name = "id") val id: String,
            @Json(name = "logo") val logo: String,
            @Json(name = "name") val name: String,
            @Json(name = "national") val national: Boolean
        )

        @JsonClass(generateAdapter = true)
        data class VenueResponse(
            @Json(name = "address") val address: String?,
            @Json(name = "capacity") val capacity: Int?,
            @Json(name = "city") val city: String?,
            @Json(name = "id") val id: Int?,
            @Json(name = "image") val image: String?,
            @Json(name = "name") val name: String?,
            @Json(name = "surface") val surface: String?
        )
    }
}