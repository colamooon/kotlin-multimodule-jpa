package com.colamooon.shop.library.sample.repository.impl

import com.colamooon.shop.library.RepoTestBase
import com.colamooon.shop.library.item.repository.ItemRepository
import com.colamooon.shop.library.model.Sample
import com.colamooon.shop.library.model.SampleStatus
import com.colamooon.shop.library.sample.repository.SampleRepository
import mu.KotlinLogging
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

private val log = KotlinLogging.logger {}

internal class SampleRepositoryImplTest @Autowired constructor(
    val sampleRepository: SampleRepository,
    val itemRepository: ItemRepository
) : RepoTestBase() {

    @BeforeEach
    fun setUp() {
        val sample = Sample("sample", SampleStatus.INIT)
        sampleRepository.save(sample)
    }

    @Test
    internal fun `equals`() {
        val sample1 = sampleRepository.getOne(1)
        val sample2 = sampleRepository.findByIdAndActive(1, true)
        log.debug("]------] SampleRepositoryImplTest::equals.sample1 [-----[ ${sample1}")
        log.debug("]------] SampleRepositoryImplTest::equals.sample1 [-----[ ${sample2}")
        // expect
        assertEquals(sample1, sample2)
    }
}