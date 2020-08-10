package com.colamooon.shop.application.common.config.security.authorization

import com.auth0.jwt.interfaces.DecodedJWT
import com.colamooon.shop.application.common.config.security.JWTService
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.stereotype.Component

@Component
class JWTAuthorizationManager(private val jwtService: JWTService) : JWTReactiveAuthorizationManager {
    override suspend fun getJwtService(): JWTService {
        return jwtService
    }

    override suspend fun doAuthorization(jwtToken: DecodedJWT): AuthorizationDecision {
        return AuthorizationDecision(true)
    }
}