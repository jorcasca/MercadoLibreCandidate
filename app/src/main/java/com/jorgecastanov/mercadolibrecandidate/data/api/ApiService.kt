package com.jorgecastanov.mercadolibrecandidate.data.api

import com.jorgecastanov.mercadolibrecandidate.data.model.Search
import retrofit2.http.GET
import retrofit2.http.Query

private const val SITE_ID = "MLA"

interface ApiService {

    @GET("/sites/$SITE_ID/search")
    suspend fun getProducts(
        @Query("q") keyWord: String
    ): Search

}
