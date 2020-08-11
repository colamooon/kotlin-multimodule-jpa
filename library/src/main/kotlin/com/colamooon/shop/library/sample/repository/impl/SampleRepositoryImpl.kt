package com.colamooon.shop.library.sample.repository.impl

import com.colamooon.shop.library.model.QSample
import com.colamooon.shop.library.model.Sample
import com.colamooon.shop.library.sample.repository.SampleDslRepository
import com.colamooon.shop.library.support.QuerydslCustomRepositorySupport
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

class SampleRepositoryImpl : QuerydslCustomRepositorySupport(Sample::class.java), SampleDslRepository {
    override fun findAllByPage(pageable: Pageable): Page<Sample> {
        val sample = QSample.sample
        val query = select(sample)
            .from(sample)
            .where(sample.active.eq(true))
        val products: List<Sample> = querydsl!!.applyPagination(pageable, query).fetch()
        return PageImpl(products, pageable, query.fetchCount())
    }
}