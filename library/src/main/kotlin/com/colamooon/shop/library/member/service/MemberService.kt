package com.colamooon.shop.library.member.service

import com.colamooon.shop.library.member.SignupReq
import com.colamooon.shop.library.member.SignupRes
import com.colamooon.shop.library.model.Member
import reactor.core.publisher.Mono

interface MemberService {
    fun signup(signupReq: SignupReq): SignupRes
}