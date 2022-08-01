package com.jorgecastanov.mercadolibrecandidate.data.datasource

import com.jorgecastanov.mercadolibrecandidate.data.api.ProductApi
import com.jorgecastanov.mercadolibrecandidate.data.api.utils.ResponseException
import com.jorgecastanov.mercadolibrecandidate.data.api.utils.isErrorCode
import com.jorgecastanov.mercadolibrecandidate.data.model.Product

class ProductRemoteDataSource(private val productApi: ProductApi) : ProductDataSource {

    override suspend fun getProducts(keyWord: String): Result<List<Product>> = try {
        val response = productApi.getProducts(keyWord)
        val errorCode = response.isErrorCode()
        when {
            errorCode != null -> Result.failure(errorCode)
            response.isSuccessful -> {
                val body = response.body()
                if (body != null) {
                    Result.success(body.results)
                } else {
                    Result.failure(ResponseException.NotContentException())
                }
            }
            else -> Result.failure(ResponseException.InvalidRequestException())
        }
    } catch (e: Exception) {
        Result.failure(ResponseException.UnknownErrorException())
    }

}
