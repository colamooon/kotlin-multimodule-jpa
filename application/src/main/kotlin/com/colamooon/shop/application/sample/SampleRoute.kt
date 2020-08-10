package com.colamooon.shop.application.sample

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter


@Configuration
class SampleRouter {
    val basePath = "/api/v1/sample"

    @Bean
    fun sampleRoute(handler: SampleHandler) = coRouter {
        path(basePath).nest {
            accept(APPLICATION_JSON).nest {
                GET("", handler::listSamples)
                GET("/{id}", handler::getSample)
            }
            POST("", handler::createSample)
        }
    }
}