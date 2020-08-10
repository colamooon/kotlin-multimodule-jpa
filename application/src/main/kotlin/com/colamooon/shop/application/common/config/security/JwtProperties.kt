package com.colamooon.shop.application.common.config.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    val secret: String = "secret",
    val refresh: String = "refresh",
    val expiredMinute: Long = 43200
)