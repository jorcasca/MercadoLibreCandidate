package com.jorgecastanov.mercadolibrecandidate.data.model

import com.squareup.moshi.Json

data class Search(
    @Json(name = "site_id")
    val site_id: String = "",
    @Json(name = "results")
    val results: List<Product>
)