package com.jorgecastanov.mercadolibrecandidate.data.datasource

import com.jorgecastanov.mercadolibrecandidate.data.model.Product

interface ProductDataSource {

    suspend fun getProducts(keyWord: String): Result<List<Product>>

}
