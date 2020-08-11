package com.colamooon.shop.application.common.config.security.authentication

import com.colamooon.shop.library.member.repository.MemberRepository
import com.colamooon.shop.library.model.SnsType
import mu.KotlinLogging
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

private val log = KotlinLogging.logger {}

@Service
class CustomerReactiveUserDetailsService(
    private val memberRepository: MemberRepository
) : ReactiveUserDetailsService {
    @Transactional(readOnly = true)
    override fun findByUsername(username: String?): Mono<UserDetails> {
        val member = memberRepository.findBySnsIdAndSnsTypeAndActive(username!!, SnsType.EMAIL, true)
            ?: throw BadCredentialsException("Invalid Credentials")
        log.debug { "]-----] CustomerReactiveUserDetailsService::findByUsername.member[-----[ ${member}" }
//        val roles = member.roles
        val authorities = member.roles.map { memberRole -> SimpleGrantedAuthority(memberRole.role.type.name) }
        return Mono.just(User(member.snsId, member.password, authorities))
    }
}