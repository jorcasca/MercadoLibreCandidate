package com.jorgecastanov.mercadolibrecandidate.data.repository

import com.jorgecastanov.mercadolibrecandidate.data.api.ApiHelper
import com.jorgecastanov.mercadolibrecandidate.data.model.Product

class FeedRepositoryImpl(private val apiHelper: ApiHelper) : FeedRepository {

    override suspend fun getProducts(keyWord: String): List<Product> =
        apiHelper.getProducts(keyWord)

}
