package com.colamooon.shop.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.reactive.config.EnableWebFlux

@EnableAsync
@EnableWebFlux
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = ["com.colamooon.shop"])
@EntityScan(basePackages = ["com.colamooon.shop"])
@EnableJpaRepositories(basePackages = ["com.colamooon.shop"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
