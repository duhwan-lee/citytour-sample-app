package com.dh.sample.citytoursampleapp.adapter.`in`.data.request

import com.dh.sample.citytoursampleapp.domain.exception.CityTourException
import com.dh.sample.citytoursampleapp.domain.exception.ErrorType
import com.dh.sample.citytoursampleapp.infrastructure.JsonDateDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import jakarta.validation.constraints.NotNull
import java.time.OffsetDateTime

data class TourUpdateRequest(
    @field:NotNull val cityId: Long,

    @JsonDeserialize(using = JsonDateDeserializer::class)
    val startDate: OffsetDateTime,
    @JsonDeserialize(using = JsonDateDeserializer::class)
    val endDate: OffsetDateTime,
) {
    fun validate() {
        if (endDate.isBefore(startDate)) {
            throw CityTourException(ErrorType.SOONER_THAN_START_DATE)
        }
    }
}