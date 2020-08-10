package com.colamooon.shop.library.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class AppContext {
    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STANDARD
        modelMapper.configuration.isAmbiguityIgnored = false
        return modelMapper
    }

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @Bean
    open fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManager);
    }
}