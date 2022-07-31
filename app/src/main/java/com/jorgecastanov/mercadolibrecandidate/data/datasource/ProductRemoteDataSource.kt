package com.jorgecastanov.mercadolibrecandidate.data.datasource

import com.jorgecastanov.mercadolibrecandidate.data.api.ProductApi
import com.jorgecastanov.mercadolibrecandidate.data.model.Product

class ProductRemoteDataSource(private val productApi: ProductApi) : ProductDataSource {

    override suspend fun getProducts(keyWord: String): List<Product> =
        productApi.getProducts(keyWord).results

}
