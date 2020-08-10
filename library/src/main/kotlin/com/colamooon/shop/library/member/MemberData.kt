package com.colamooon.shop.library.member

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SigninReq(
    @field:Email(regexp = ".+@.+\\..+")
    @field:NotBlank
    val username: String,
    @field:NotBlank
    @field:Size(min = 8, max = 255)
    val password: String
)

data class MemberRes(
    @field:Email(regexp = ".+@.+\\..+")
    @field:NotBlank
    val username: String,
    @field:NotBlank
    @field:Size(min = 8, max = 255)
    val password: String
)