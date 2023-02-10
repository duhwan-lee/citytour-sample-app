package com.dh.sample.citytoursampleapp.domain

import java.time.OffsetDateTime

data class Tour(
    val userId: Long,
    val city: City,
    val startDate: OffsetDateTime,
    val endDate: OffsetDateTime
) {

}