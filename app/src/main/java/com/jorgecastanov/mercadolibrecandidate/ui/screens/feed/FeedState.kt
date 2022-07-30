package com.jorgecastanov.mercadolibrecandidate.ui.screens.feed

import com.jorgecastanov.mercadolibrecandidate.data.model.Product

sealed class FeedState {

    object Idle : FeedState()
    object Loading : FeedState()
    data class Products(val products: List<Product>) : FeedState()
    data class Error(val error: String?) : FeedState()

}