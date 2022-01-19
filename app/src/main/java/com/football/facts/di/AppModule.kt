package com.football.facts.di

import android.content.Context
import com.football.facts.ui.utils.broadcast.BroadcastManager
import com.football.facts.ui.utils.navigation.NavigationManager
import com.football.facts.ui.utils.notification.ToastManager
import com.football.facts.ui.utils.resource.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNavigationManager() : NavigationManager {
        return NavigationManager()
    }

    @Singleton
    @Provides
    fun provideToastManager() : ToastManager {
        return ToastManager()
    }

    @Singleton
    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context) : ResourceProvider{
        return ResourceProvider(context)
    }

    @Singleton
    @Provides
    fun provideBroadcastManager() : BroadcastManager{
        return BroadcastManager()
    }
}