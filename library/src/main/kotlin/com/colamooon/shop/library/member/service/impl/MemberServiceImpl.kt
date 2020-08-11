package com.colamooon.shop.library.member.service.impl

import com.colamooon.shop.library.exception.ErrorMessageCode
import com.colamooon.shop.library.exception.SignupException
import com.colamooon.shop.library.member.SignupReq
import com.colamooon.shop.library.member.SignupRes
import com.colamooon.shop.library.member.repository.MemberRepository
import com.colamooon.shop.library.member.repository.MemberRoleRepository
import com.colamooon.shop.library.member.repository.RoleRepository
import com.colamooon.shop.library.member.service.MemberService
import com.colamooon.shop.library.model.Member
import com.colamooon.shop.library.model.MemberRole
import com.colamooon.shop.library.model.RoleType
import com.colamooon.shop.library.model.SnsType
import mu.KotlinLogging
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

private val log = KotlinLogging.logger {}

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val roleRepository: RoleRepository,
    private val mapper: ModelMapper
) : MemberService {
    @Transactional
    override fun signup(req: SignupReq): SignupRes {
        log.debug { "]-----] MemberServiceImpl::signup SignupReq[-----[ ${req}" }
        val exists = memberRepository.findBySnsIdAndSnsTypeAndActive(req.snsId, SnsType.EMAIL, true)
        if (exists != null) {
            throw SignupException(ErrorMessageCode.SNSID_ALREADY_EXISTS)
        }
        var newMember = Member(snsId = req.snsId, snsType = SnsType.EMAIL, password = req.password)
        memberRepository.save(newMember)
        val role = roleRepository.findByType(RoleType.ROLE_USER)
            ?: throw EntityNotFoundException("not found a Role(type = ${RoleType.ROLE_USER.name})")
        memberRoleRepository.save(MemberRole(newMember, role))
        log.debug { "]-----] MemberServiceImpl::signup newMember[-----[ ${newMember}" }
        return SignupRes(newMember.id!!)
    }
}