package com.sandbox.exception

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true, value = ["message", "localizedMessage", "suppressed", "stackTrace", "cause", "httpStatus"])
data class ApiException(private val apiError: ApiError): RuntimeException() {

    val httpStatus = apiError.httpStatus
    val appCode = apiError.appCode
    val errorMessage = apiError.message
    val details: Map<String, String>? = null
}