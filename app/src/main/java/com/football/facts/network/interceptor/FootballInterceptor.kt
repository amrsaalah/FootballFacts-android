package com.football.facts.network.interceptor

import com.football.facts.network.Network
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class FootballInterceptor @Inject constructor(
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("x-apisports-key", Network.API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}