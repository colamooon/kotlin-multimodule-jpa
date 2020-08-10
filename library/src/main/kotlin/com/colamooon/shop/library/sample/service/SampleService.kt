package com.colamooon.shop.library.sample.service

import com.colamooon.shop.library.sample.CreateSampleReq
import com.colamooon.shop.library.sample.CreateSampleRes
import com.colamooon.shop.library.sample.SampleRes
import org.springframework.data.domain.Page

interface SampleService {
    fun allSamples(): Page<SampleRes>
    fun findById(id: Long): SampleRes
    fun create(req: CreateSampleReq): CreateSampleRes
}