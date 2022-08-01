package com.jorgecastanov.mercadolibrecandidate.di

import com.jorgecastanov.mercadolibrecandidate.data.datasource.ProductDataSource
import com.jorgecastanov.mercadolibrecandidate.data.repository.ProductRepository
import com.jorgecastanov.mercadolibrecandidate.data.repository.ProductRepositoryImpl
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
    fun provideProductRepository(productDataSource: ProductDataSource): ProductRepository =
        ProductRepositoryImpl(productDataSource)

}
