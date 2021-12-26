package com.football.facts.network.response
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class CountriesResponse(
    @Json(name = "errors") val errors: List<Any>,
    @Json(name = "get") val `get`: String,
    @Json(name = "paging") val paging: PagingResponse,
    @Json(name = "parameters") val parameters: List<Any>,
    @Json(name = "response") val response: List<CountryResponse>,
    @Json(name = "results") val results: Int
) {

    @JsonClass(generateAdapter = true)
    data class CountryResponse(
        @Json(name = "code") val code: String?,
        @Json(name = "flag") val flag: String?,
        @Json(name = "name") val name: String
    )
}
























