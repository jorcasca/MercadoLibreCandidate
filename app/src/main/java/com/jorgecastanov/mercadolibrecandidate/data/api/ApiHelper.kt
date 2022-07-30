package com.jorgecastanov.mercadolibrecandidate.data.api

import com.jorgecastanov.mercadolibrecandidate.data.model.Product

interface ApiHelper {

    suspend fun getProducts(keyWord: String): List<Product>

}
