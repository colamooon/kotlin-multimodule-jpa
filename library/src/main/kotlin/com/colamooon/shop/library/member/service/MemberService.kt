package com.colamooon.shop.library.member.service

import com.colamooon.shop.library.member.MemberRes

interface MemberService {
    fun findByUsername(username: String): MemberRes
}