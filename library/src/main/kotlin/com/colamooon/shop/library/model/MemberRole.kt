package com.colamooon.shop.library.model

import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["member_id", "role_id"])])
data class MemberRole(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    open var member: Member,

    @ManyToOne
    @JoinColumn(name = "role_id")
    var role: Role

) : BaseEntity()
