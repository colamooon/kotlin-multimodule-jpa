package com.colamooon.shop.application.common.config.security.authentication

import com.colamooon.shop.application.common.config.HttpExceptionFactory.badRequest
import com.colamooon.shop.library.member.SigninReq
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactor.mono
import org.springframework.core.ResolvableType
import org.springframework.http.MediaType
import org.springframework.http.codec.json.AbstractJackson2Decoder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import javax.validation.Validator

@Component
class JWTConverter(private val jacksonDecoder: AbstractJackson2Decoder,
                   private val validator: Validator) : ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> = mono {
        val signinReq = getUsernameAndPassword(exchange!!) ?: throw badRequest()

        if (validator.validate(signinReq).isNotEmpty()) {
            throw badRequest()
        }

        return@mono UsernamePasswordAuthenticationToken(signinReq.username, signinReq.password)
    }

    private suspend fun getUsernameAndPassword(exchange: ServerWebExchange): SigninReq? {
        val dataBuffer = exchange.request.body
        val type = ResolvableType.forClass(SigninReq::class.java)
        return jacksonDecoder
            .decodeToMono(dataBuffer, type, MediaType.APPLICATION_JSON, mapOf())
            .onErrorResume { Mono.empty<SigninReq>() }
            .cast(SigninReq::class.java)
            .awaitFirstOrNull()
    }
}
