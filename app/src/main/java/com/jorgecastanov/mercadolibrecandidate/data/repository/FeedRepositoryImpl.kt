package com.jorgecastanov.mercadolibrecandidate.data.repository

import com.jorgecastanov.mercadolibrecandidate.data.api.ApiHelper
import com.jorgecastanov.mercadolibrecandidate.data.model.Product
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
    ) : FeedRepository {

    override suspend fun getProducts(keyWord: String): List<Product> =
        apiHelper.getProducts(keyWord)

}
