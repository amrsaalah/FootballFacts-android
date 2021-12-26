package com.football.facts.di

import android.content.Context
import androidx.room.Room
import com.football.facts.data.FootballDatabase
import com.football.facts.data.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Singleton
    @Provides
    fun provideFootballDatabase(@ApplicationContext context: Context): FootballDatabase {
        return Room.databaseBuilder(
            context,
            FootballDatabase::class.java,
            FootballDatabase.DATABASE_NAME
        ).build()
    }


    @Singleton
    @Provides
    fun provideFavoriteDao(footballDatabase: FootballDatabase): FavoriteDao {
        return footballDatabase.favoriteDao()
    }
}