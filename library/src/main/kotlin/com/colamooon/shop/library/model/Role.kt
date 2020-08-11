package com.colamooon.shop.library.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Role(
    @Enumerated(EnumType.STRING)
    @Column(unique = true, length = 10)
    var type: RoleType,
    override var id: Long? = null
) : BaseEntity()

enum class RoleType {
    ROLE_GUEST, ROLE_USER,
}
