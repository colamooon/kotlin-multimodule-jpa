package com.colamooon.shop.application.auth

import com.colamooon.shop.library.member.SignupReq
import com.colamooon.shop.library.member.service.MemberService
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.server.ServerWebInputException

@Component
class AuthHandler(
    private val memberService: MemberService,
    private val passwordEncoder: PasswordEncoder
) {
    private val validator = AuthValidator()

    suspend fun signup(request: ServerRequest): ServerResponse {
        val signupReq = request.awaitBody<SignupReq>()
        validate(signupReq)
        return memberService.signup(signupReq.also { it.password = passwordEncoder.encode(it.password) })
            .let {
                ok().contentType(APPLICATION_JSON).bodyValueAndAwait(it)
            }
    }

    suspend fun signin(request: ServerRequest): ServerResponse {
        val signupReq = request.awaitBody<SignupReq>()
//        validate(signupReq)
        return ok().contentType(APPLICATION_JSON).bodyValueAndAwait("Hello World")

    }

    private fun validate(signupReq: SignupReq) {
        val errors: Errors = BeanPropertyBindingResult(signupReq, "signupReq");
        validator.validate(signupReq, errors);
        if (errors.hasErrors()) {
            throw ServerWebInputException(errors.toString())
        }
    }

}