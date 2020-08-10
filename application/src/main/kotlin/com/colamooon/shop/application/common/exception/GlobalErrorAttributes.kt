package com.colamooon.shop.application.common.exception

import com.colamooon.shop.library.exception.ApplicationException
import mu.KotlinLogging
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import java.util.*
import kotlin.collections.LinkedHashMap

private val log = KotlinLogging.logger {}

@Component
class GlobalErrorAttributes : ErrorAttributes {
    val ERROR_ATTRIBUTE = GlobalErrorAttributes::class.java.name + ".ERROR";

    override fun getErrorAttributes(request: ServerRequest, options: ErrorAttributeOptions): MutableMap<String, Any>? {
        var errorAttributes: MutableMap<String, Any> = LinkedHashMap()
        var status = HttpStatus.INTERNAL_SERVER_ERROR
        val error = getError(request)
        var message = error.message!!
        if (error is ResponseStatusException) {
            message = error.reason!!
            status = error.status
        }
        if (error is ApplicationException) {
            message = error.errorMessageCode.message
            errorAttributes["code"] = error.errorMessageCode.code
        }
        errorAttributes["timestamp"] = Date()
        errorAttributes["path"] = request.path()
        errorAttributes["message"] = message
        errorAttributes["status"] = status.value()
        errorAttributes["error"] = HttpStatus.valueOf(status.value()).reasonPhrase
        return errorAttributes
    }

    override fun getError(request: ServerRequest): Throwable {
        return request.attribute(ERROR_ATTRIBUTE).orElseThrow() as Throwable
    }

    override fun storeErrorInformation(error: Throwable, exchange: ServerWebExchange) {
        exchange.attributes.putIfAbsent(ERROR_ATTRIBUTE, error)
    }

}