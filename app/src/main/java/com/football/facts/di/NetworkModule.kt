package com.football.facts.di

import com.football.facts.network.Network
import com.football.facts.network.interceptor.FootballInterceptor
import com.football.facts.network.service.FootballApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideFootballApiService(footballInterceptor: FootballInterceptor) : FootballApiService{

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor = logInterceptor)
            .addInterceptor(interceptor = footballInterceptor)
            .build()

        val moshi = Moshi.Builder().build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(Network.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        return retrofit.create(FootballApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideFootballInterceptor() : FootballInterceptor{
        return FootballInterceptor()
    }
}