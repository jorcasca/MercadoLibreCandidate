package com.jorgecastanov.mercadolibrecandidate.data.repository

import com.jorgecastanov.mercadolibrecandidate.data.model.Product

interface FeedRepository {

    suspend fun getProducts(keyWord: String): List<Product>

}
