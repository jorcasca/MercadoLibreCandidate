package com.jorgecastanov.mercadolibrecandidate.data.repository

import com.jorgecastanov.mercadolibrecandidate.data.model.Product

interface ProductRepository {

    suspend fun getProducts(keyWord: String): Result<List<Product>>

}
