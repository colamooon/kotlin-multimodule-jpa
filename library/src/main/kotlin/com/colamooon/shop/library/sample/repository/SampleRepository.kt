package com.colamooon.shop.library.sample.repository

import com.colamooon.shop.library.model.Sample
import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository : JpaRepository<Sample, Long>, SampleDslRepository {
    fun findByTitleAndActive(title: String, active: Boolean): Sample?
    fun findByIdAndActive(id: Long, active: Boolean): Sample?
}