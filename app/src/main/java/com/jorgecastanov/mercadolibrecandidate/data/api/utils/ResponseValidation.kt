package com.jorgecastanov.mercadolibrecandidate.data.api.utils

import retrofit2.Response

fun <T> Response<T>.isErrorCode(): Exception? {
    return ResponseException.getException(code())
}
