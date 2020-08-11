package com.colamooon.shop.library.member.repository

import com.colamooon.shop.library.model.Member
import com.colamooon.shop.library.model.SnsType
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findBySnsIdAndSnsTypeAndActive(snsId: String, snsType: SnsType, active: Boolean): Member?
}