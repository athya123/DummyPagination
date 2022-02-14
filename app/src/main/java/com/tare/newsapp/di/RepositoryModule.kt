package com.tare.newsapp.di

import com.google.android.gms.ads.AdLoader
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.tare.newsapp.R
import com.tare.newsapp.network.PagingSource
import com.tare.newsapp.network.Services
import com.tare.newsapp.pojo.entities.Article
import com.tare.newsapp.ui.HomeRepository
import com.tare.newsapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideConstants(): Constants {
        return Constants
    }

    @Singleton
    @Provides
    fun providePagingSource(
        services: Services,
    ): PagingSource {
        return PagingSource(services)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(
        pagingSource: PagingSource,
    ): HomeRepository {
        return HomeRepository(pagingSource)
    }
}