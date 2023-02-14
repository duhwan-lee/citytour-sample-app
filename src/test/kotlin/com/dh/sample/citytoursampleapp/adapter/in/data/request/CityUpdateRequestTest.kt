package com.dh.sample.citytoursampleapp.adapter.`in`.data.request

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CityUpdateRequestTest{
    private val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun testValidation() {
        val emptyRequest = CityUpdateRequest("")
        val emptyValidate = validator.validate(emptyRequest)
        assertThat(emptyValidate).hasSize(1)

        val notEmptyRequest = CityUpdateRequest("seoul")
        val notEmptyValidate = validator.validate(notEmptyRequest)
        assertThat(notEmptyValidate).hasSize(0)
    }
}