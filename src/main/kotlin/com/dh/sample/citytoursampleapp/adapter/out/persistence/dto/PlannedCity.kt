package com.dh.sample.citytoursampleapp.adapter.out.persistence.dto

import java.time.OffsetDateTime

interface PlannedCity {
    fun getStartDate(): OffsetDateTime
    fun getCityId(): Long
    fun getCityName(): String

}