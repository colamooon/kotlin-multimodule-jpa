package com.colamooon.shop.application.common.config

import com.colamooon.shop.application.common.config.security.JWTService
import com.colamooon.shop.application.common.config.security.authentication.CustomerReactiveUserDetailsService
import com.colamooon.shop.application.common.config.security.authorization.JWTAuthorizationManager
import com.colamooon.shop.application.common.config.security.authorization.JWTRoleAuthorizationManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.codec.json.AbstractJackson2Decoder
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import org.springframework.web.reactive.config.WebFluxConfigurer


@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig : WebFluxConfigurer {

    companion object {
        val EXCLUDED_PATHS = arrayOf("/api/v1/auth/signup")
    }

    @Bean
    fun configureSecurity(http: ServerHttpSecurity,
                          jwtAuthenticationFilter: AuthenticationWebFilter,
                          jwtAuthorizationManager: JWTAuthorizationManager,
                          jwtService: JWTService): SecurityWebFilterChain {
        return http
            .csrf().disable()
            .logout().disable()
            .authorizeExchange()
            .pathMatchers(*EXCLUDED_PATHS).permitAll()
            .pathMatchers("/api/**").access(JWTRoleAuthorizationManager(jwtService, "USER"))
            .anyExchange().access(jwtAuthorizationManager)
            .and()
            .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationWebFilter(reactiveAuthenticationManager: ReactiveAuthenticationManager,
                                jwtConverter: ServerAuthenticationConverter,
                                serverAuthenticationSuccessHandler: ServerAuthenticationSuccessHandler,
                                jwtServerAuthenticationFailureHandler: ServerAuthenticationFailureHandler): AuthenticationWebFilter {
        val authenticationWebFilter = AuthenticationWebFilter(reactiveAuthenticationManager)
        authenticationWebFilter.setRequiresAuthenticationMatcher { ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/api/v1/auth/signin").matches(it) }
        authenticationWebFilter.setServerAuthenticationConverter(jwtConverter)
        authenticationWebFilter.setAuthenticationSuccessHandler(serverAuthenticationSuccessHandler)
        authenticationWebFilter.setAuthenticationFailureHandler(jwtServerAuthenticationFailureHandler)
        authenticationWebFilter.setSecurityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        return authenticationWebFilter
    }

    @Bean
    fun jacksonDecoder(): AbstractJackson2Decoder = Jackson2JsonDecoder()

    @Bean
    fun reactiveAuthenticationManager(reactiveUserDetailsService: CustomerReactiveUserDetailsService,
                                      passwordEncoder: PasswordEncoder): ReactiveAuthenticationManager {
        val manager = UserDetailsRepositoryReactiveAuthenticationManager(reactiveUserDetailsService)
        manager.setPasswordEncoder(passwordEncoder)
        return manager
    }
}

