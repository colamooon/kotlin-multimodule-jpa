package com.colamooon.shop.library.model

import javax.persistence.*

@Entity
@Table(name = "member_cola")
class Member(
    @Column(length = 100)
    var snsId: String,
    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    var snsType: SnsType,
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    var status: MemberStatus = MemberStatus.ACTIVE,
    var password: String? = null,
    var snsToken: String? = null,
    var realName: String? = null,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "member")
    var roles: MutableSet<MemberRole> = mutableSetOf()

) : BaseEntity() {
    internal fun addRole(memberRole: MemberRole) {
        roles.add(memberRole)
        memberRole.member = this
    }
}

enum class MemberStatus {
    ACTIVE, INACTIVE, LEAVE
}

enum class SnsType {
    EMAIL, FACEBOOK, KAKAO, GOOGLE, APPLE,
}