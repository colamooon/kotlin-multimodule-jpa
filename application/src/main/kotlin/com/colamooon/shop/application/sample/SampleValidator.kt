package com.colamooon.shop.application.sample

import com.colamooon.shop.library.sample.CreateSampleReq
import org.apache.commons.lang3.StringUtils
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

class SampleValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return CreateSampleReq::class.java == clazz
    }

    override fun validate(obj: Any, e: Errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "title", "title.required")
        val sample = obj as CreateSampleReq
        if (StringUtils.isEmpty(sample.title)) {
            e.rejectValue("title", "title.required")
        }
    }
}