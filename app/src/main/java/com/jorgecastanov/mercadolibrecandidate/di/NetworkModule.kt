package com.jorgecastanov.mercadolibrecandidate.di

import com.jorgecastanov.mercadolibrecandidate.data.api.ProductApi
import com.jorgecastanov.mercadolibrecandidate.data.datasource.ProductDataSource
import com.jorgecastanov.mercadolibrecandidate.data.datasource.ProductRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseURL(): String = "https://api.mercadolibre.com"

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideProductApi(retrofit: Retrofit): ProductApi = retrofit.create(ProductApi::class.java)

    @Singleton
    @Provides
    fun provideProductDataSource(apiService: ProductApi): ProductDataSource = ProductRemoteDataSource(apiService)

}
