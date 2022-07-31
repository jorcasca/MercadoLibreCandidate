package com.jorgecastanov.mercadolibrecandidate.di

import com.jorgecastanov.mercadolibrecandidate.data.api.ApiHelper
import com.jorgecastanov.mercadolibrecandidate.data.repository.FeedRepository
import com.jorgecastanov.mercadolibrecandidate.data.repository.FeedRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFeedRepository(apiHelper: ApiHelper): FeedRepository =
        FeedRepositoryImpl(apiHelper)

}
