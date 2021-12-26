package com.football.facts.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PagingResponse(
    @Json(name = "current") val current: Int,
    @Json(name = "total") val total: Int
)
