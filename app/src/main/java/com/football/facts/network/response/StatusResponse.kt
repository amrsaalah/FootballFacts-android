package com.football.facts.network.response
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class StatusResponse(
    @Json(name = "errors") val errors: List<Any>,
    @Json(name = "get") val `get`: String,
    @Json(name = "paging") val paging: PagingResponse,
    @Json(name = "parameters") val parameters: List<Any>,
    @Json(name = "response") val response: ResponseResponse,
    @Json(name = "results") val results: Int
) {

    @JsonClass(generateAdapter = true)
    data class ResponseResponse(
        @Json(name = "account") val account: AccountResponse,
        @Json(name = "requests") val requests: RequestsResponse,
        @Json(name = "subscription") val subscription: SubscriptionResponse
    ) {
        @JsonClass(generateAdapter = true)
        data class AccountResponse(
            @Json(name = "email") val email: String,
            @Json(name = "firstname") val firstname: String,
            @Json(name = "lastname") val lastname: String
        )

        @JsonClass(generateAdapter = true)
        data class RequestsResponse(
            @Json(name = "current") val current: Int,
            @Json(name = "limit_day") val limitDay: Int
        )

        @JsonClass(generateAdapter = true)
        data class SubscriptionResponse(
            @Json(name = "active") val active: Boolean,
            @Json(name = "end") val end: String,
            @Json(name = "plan") val plan: String
        )
    }
}