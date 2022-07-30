package com.jorgecastanov.mercadolibrecandidate.data.api

import com.jorgecastanov.mercadolibrecandidate.data.model.Product

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getProducts(keyWord: String): List<Product> =
        apiService.getProducts(keyWord).results

}
