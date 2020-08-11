package com.colamooon.shop.application.auth

import com.colamooon.shop.library.member.SignupReq
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

class AuthValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return SignupReq::class.java == clazz
    }

    override fun validate(obj: Any, e: Errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "snsId", "snsId.required")
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "password", "password.required")
    }
}