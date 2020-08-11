package com.colamooon.shop.application.sample

import com.colamooon.shop.library.sample.CreateSampleReq
import com.colamooon.shop.library.sample.service.SampleService
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.server.ServerWebInputException

@Component
class SampleHandler(
    private val sampleService: SampleService
) {
    private val validator = SampleValidator()
    suspend fun listSamples(request: ServerRequest): ServerResponse {
        val samples = sampleService.allSamples()
        return ok().contentType(APPLICATION_JSON).bodyValueAndAwait(samples);
    }

    suspend fun getSample(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        return sampleService.findById(id).let {
            ok().contentType(APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }

    suspend fun createSample(request: ServerRequest): ServerResponse {
        val sampleReq = request.awaitBody<CreateSampleReq>()
        validate(sampleReq)
        return sampleService.create(sampleReq).let {
            ok().contentType(APPLICATION_JSON).bodyValueAndAwait(it)
        }
    }

    private fun validate(sample: CreateSampleReq) {
        val errors: Errors = BeanPropertyBindingResult(sample, "sample");
//        validator.validate(sample, errors);
        if (errors.hasErrors()) {
            throw ServerWebInputException(errors.toString())
        }
    }

}