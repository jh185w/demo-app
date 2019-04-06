package com.sandbox.autoconfigure.web

import org.slf4j.LoggerFactory
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class WebExecutionTimeLogInterceptor: HandlerInterceptorAdapter() {

    companion object {
        private val LOG = LoggerFactory.getLogger(WebExecutionTimeLogInterceptor::class.java)
        private const val START_TIME_ATTR = "startTime"
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.setAttribute(START_TIME_ATTR, System.currentTimeMillis())
        return true
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        val startTime = request.getAttribute(START_TIME_ATTR) as Long
        LOG.info("** Execution time for request to ${request.requestURL} - ${System.currentTimeMillis() - startTime}ms")
    }
}