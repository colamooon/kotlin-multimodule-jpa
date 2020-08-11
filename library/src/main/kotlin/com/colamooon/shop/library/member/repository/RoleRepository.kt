package com.colamooon.shop.library.member.repository

import com.colamooon.shop.library.model.Role
import com.colamooon.shop.library.model.RoleType
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findByType(type: RoleType): Role?
}