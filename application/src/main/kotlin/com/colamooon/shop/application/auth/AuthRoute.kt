package com.colamooon.shop.application.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AuthRouter {
    val basePath = "/api/v1/auth"

    @Bean
    fun authRoute(handler: AuthHandler) = coRouter {
        path(basePath).nest {
            POST("/signup", handler::signup)
            POST("/signin", handler::signin)
        }
    }
}