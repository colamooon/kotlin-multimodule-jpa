package com.colamooon.shop.library.repository

import com.colamooon.shop.library.RepoTestBase
import com.colamooon.shop.library.model.Sample
import com.colamooon.shop.library.model.SampleStatus
import com.colamooon.shop.library.sample.repository.SampleRepository
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SampleRepositoryTest : RepoTestBase() {
    @Autowired
    lateinit var sampleRepository: SampleRepository



    @Test
    fun `When findByTitle then return Sample`() {
        val sample = Sample("sample", SampleStatus.INIT)
        sampleRepository.save(sample)

        val found = sampleRepository.findByTitleAndActive(sample.title, true)
        found.shouldBe(sample)
    }
}