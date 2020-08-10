package com.colamooon.shop.library.member.repository

import com.colamooon.shop.library.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByUsernameAndActive(username: String, active: Boolean): Member?
}