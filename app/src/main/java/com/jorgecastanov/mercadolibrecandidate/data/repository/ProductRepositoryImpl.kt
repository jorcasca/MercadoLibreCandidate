package com.jorgecastanov.mercadolibrecandidate.data.repository

import com.jorgecastanov.mercadolibrecandidate.data.datasource.ProductDataSource
import com.jorgecastanov.mercadolibrecandidate.data.model.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource
    ) : ProductRepository {

    override suspend fun getProducts(keyWord: String): List<Product> =
        productDataSource.getProducts(keyWord)

}
