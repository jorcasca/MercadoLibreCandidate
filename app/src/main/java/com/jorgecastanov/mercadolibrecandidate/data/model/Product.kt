package com.jorgecastanov.mercadolibrecandidate.data.model

import com.squareup.moshi.Json

data class Product(
    @Json(name = "id")
    val id: String = "",
    @Json(name = "title")
    val title: String = "",
    @Json(name = "price")
    val price: Double = 0.0,
    @Json(name = "available_quantity")
    val available_quantity: Int = 0,
    @Json(name = "sold_quantity")
    val sold_quantity: Int = 0,
    @Json(name = "buying_mode")
    val buying_mode: String = "",
    @Json(name = "condition")
    val condition: String = "",
    @Json(name = "permalink")
    val permalink: String = "",
    @Json(name = "thumbnail")
    val thumbnail: String = ""
)
