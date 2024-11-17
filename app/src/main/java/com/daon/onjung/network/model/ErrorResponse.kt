package com.daon.onjung.network.model

data class ErrorResponse(
    val isSuccess: Boolean = false,
    val code: String = "",
    val message: String = ""
)
