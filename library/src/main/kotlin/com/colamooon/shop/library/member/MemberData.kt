package com.colamooon.shop.library.member

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignupReq(
    @field:Email
    @field:NotBlank
    var snsId: String,
    @field:NotBlank
    @field:Size(min = 8, max = 255)
    var password: String
)

data class SignupRes(
    var id: Long = -1
)

data class SigninReq(
    @field:Email
    @field:NotBlank
    var snsId: String,
    @field:NotBlank
    @field:Size(min = 8, max = 255)
    var password: String
)

data class MemberRes(
    var id: Long = -1
)

