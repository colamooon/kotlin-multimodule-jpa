package com.colamooon.shop.application.common.config.security.authentication

import com.colamooon.shop.library.member.repository.MemberRepository
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomerReactiveUserDetailsService(private val memberRepository: MemberRepository) : ReactiveUserDetailsService {
    override fun findByUsername(username: String?): Mono<UserDetails> = mono {
        val member = memberRepository.findByUsernameAndActive(username!!, true)
            ?: throw BadCredentialsException("Invalid Credentials")
        val authorities = member.roles.map { memberRole -> SimpleGrantedAuthority(memberRole.role.type.name) }
        return@mono User(member.username, member.password, authorities)
    }
}