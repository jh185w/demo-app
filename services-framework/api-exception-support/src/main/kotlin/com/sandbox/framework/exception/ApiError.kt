package com.sandbox.exception

import org.springframework.http.HttpStatus

enum class ApiError(val httpStatus: HttpStatus, val appCode: String, val message: String) {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "ERR-1000", "Request has invalid or missing inputs"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "ERR-1001", "The requested item(s) could not found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"ERR-1002", "Internal server processing error"),
    DOWNSTREAM_SERVER_UNREACHABLE(HttpStatus.INTERNAL_SERVER_ERROR,"ERR-1003", "Downstream server unreachable")
}