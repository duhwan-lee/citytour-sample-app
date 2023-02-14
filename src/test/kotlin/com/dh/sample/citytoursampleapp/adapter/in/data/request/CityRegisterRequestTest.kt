package com.dh.sample.citytoursampleapp.adapter.`in`.data.request

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CityRegisterRequestTest {
    private val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun testValidation() {
        val emptyRequest1 = CityRegisterRequest("", "korea")
        val emptyValidate1 = validator.validate(emptyRequest1)
        assertThat(emptyValidate1).hasSize(1)

        val emptyRequest2 = CityRegisterRequest("", "")
        val emptyValidate2 = validator.validate(emptyRequest2)
        assertThat(emptyValidate2).hasSize(2)

        val notEmptyRequest = CityRegisterRequest("seoul", "korea")
        val notEmptyValidate = validator.validate(notEmptyRequest)
        assertThat(notEmptyValidate).hasSize(0)
    }
}