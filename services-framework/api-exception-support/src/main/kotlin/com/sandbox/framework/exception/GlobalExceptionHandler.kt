package com.sandbox.exception

import com.netflix.client.ClientException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class GlobalExceptionHandler {

    //Handle exceptions when Ribbon cannot resolve available service from Eureka
    @ExceptionHandler(ClientException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun onClientException(e: ClientException) = ApiException(ApiError.DOWNSTREAM_SERVER_UNREACHABLE)

    //Handle exceptions when FeignClient returns error from downstream service
    @ExceptionHandler(ApiException::class)
    @ResponseBody
    fun onApiException(e: ApiException): ResponseEntity<ApiException> {
        return ResponseEntity.status(e.httpStatus)
                .body(e)
    }

}