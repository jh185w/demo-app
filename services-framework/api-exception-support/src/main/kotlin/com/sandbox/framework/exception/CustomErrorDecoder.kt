package com.sandbox.exception

import feign.Response
import feign.codec.ErrorDecoder
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
@Primary
class CustomErrorDecoder : ErrorDecoder {

    companion object {
        private val LOG = LoggerFactory.getLogger(CustomErrorDecoder::class.java)
    }

    override fun decode(methodKey: String?, resp: Response?): Exception {
        //Wrap any exception returned to FeignClient in an ApiException
        LOG.error("Error calling downstream service: " +
                "callingMethod=$methodKey?; httpCode=${resp?.status()}; responseBody=${resp?.bodyAsString()}")

        throw ApiException(getApiError(resp?.status()))
    }

    private fun Response.bodyAsString(): String = this.body()
            .asInputStream()
            .bufferedReader()
            .use { it.readText() }

    private fun getApiError(status: Int?): ApiError {
        return when (status) {
            HttpStatus.BAD_REQUEST.value() -> ApiError.BAD_REQUEST
            HttpStatus.NOT_FOUND.value() -> ApiError.NOT_FOUND
            else -> ApiError.INTERNAL_SERVER_ERROR
        }
    }
}