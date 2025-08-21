package com.iwelogic.jedyapp.data.base

sealed class AppFailure(
    override val message: String?
) : Throwable(message) {
    data class UnknownFailure(override val message: String?) : AppFailure(message)
    data class ResponseFailure(override val message: String?) : AppFailure(message)
}