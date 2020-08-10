package com.colamooon.shop.library.sample.repository

import com.colamooon.shop.library.model.Sample
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SampleDslRepository {
    fun findAllByPage(pageable: Pageable): Page<Sample>
}