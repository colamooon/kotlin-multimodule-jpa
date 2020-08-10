package com.colamooon.shop.library.model

import org.apache.commons.lang3.builder.ToStringBuilder
import javax.persistence.*

@Entity
@Table(name = "member_cola")
class Member(
    @Column(length = 100)
    val username: String,
    val password: String,
    @Enumerated(EnumType.STRING)
    val status: MemberStatus = MemberStatus.ACTIVE,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "member")
    val roles: MutableSet<MemberRole> = mutableSetOf()
) : BaseEntity() {
    override fun toString(): String = ToStringBuilder.reflectionToString(this)
}

enum class MemberStatus {
    ACTIVE, INACTIVE, LEAVE
}