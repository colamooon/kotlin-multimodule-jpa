package com.colamooon.shop.library.member.repository

import com.colamooon.shop.library.model.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRoleRepository : JpaRepository<MemberRole, Long>