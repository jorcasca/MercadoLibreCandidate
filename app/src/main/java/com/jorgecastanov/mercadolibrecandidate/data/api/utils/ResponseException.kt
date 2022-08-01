package com.jorgecastanov.mercadolibrecandidate.data.api.utils

sealed class ResponseException(message: String) : Exception(message) {

    class ServiceUnavailableException : ResponseException("Service unavailable")
    class InternalServerException : ResponseException("Internal server error")
    class InvalidRequestException : ResponseException("Invalid Request")
    class UnauthorizedTokenException : ResponseException("Unauthorized token")
    class ScopeException : ResponseException("Scope error")
    class NotFoundException : ResponseException("Not found")
    class NotContentException : ResponseException("No content")
    class UnknownErrorException : ResponseException("Unknown error")

    companion object {
        fun getException(errorCode: Int): Exception? {
            return when (errorCode) {
                503 -> ServiceUnavailableException()
                500 -> InternalServerException()
                404 -> NotFoundException()
                400 -> InvalidRequestException()
                401 -> UnauthorizedTokenException()
                403 -> ScopeException()
                else -> null
            }
        }
    }
}
