package com.colamooon.shop.application.common.config.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.util.*

@Service
class JWTService(val jwtProperties: JwtProperties) {
    private lateinit var secretKey: String
    private lateinit var refreshKey: String

    init {
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.secret.toByteArray())
        refreshKey = Base64.getEncoder().encodeToString(jwtProperties.secret.toByteArray())
    }

    fun accessToken(username: String, expirationInMillis: Int, roles: Array<String>): String {
        return generate(username, expirationInMillis, roles, secretKey)
    }

    fun decodeAccessToken(accessToken: String): DecodedJWT {
        return decode(secretKey, accessToken)
    }

    fun refreshToken(username: String, expirationInMillis: Int, roles: Array<String>): String {
        return generate(username, expirationInMillis, roles, refreshKey)
    }

    fun decodeRefreshToken(refreshToken: String): DecodedJWT {
        return decode(refreshKey, refreshToken)
    }

    fun getRoles(decodedJWT: DecodedJWT) = decodedJWT.getClaim("role").asList(String::class.java)
        .map { SimpleGrantedAuthority(it) }

    private fun generate(username: String, expirationInMillis: Int, roles: Array<String>, signature: String): String {
        return JWT.create()
            .withIssuer("com.colamooon.shop")
            .withSubject(username)
            .withExpiresAt(Date(System.currentTimeMillis() + expirationInMillis))
            .withArrayClaim("role", roles)
            .sign(Algorithm.HMAC512(signature))
    }

    private fun decode(signature: String, token: String): DecodedJWT {
        return JWT.require(Algorithm.HMAC512(signature.toByteArray()))
            .build()
            .verify(token.replace("Bearer ", ""))
    }
}