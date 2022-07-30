package com.jorgecastanov.mercadolibrecandidate.ui.screens.feed

sealed class FeedIntent {

    data class FetchProducts(val keyWord: String) : FeedIntent()

}