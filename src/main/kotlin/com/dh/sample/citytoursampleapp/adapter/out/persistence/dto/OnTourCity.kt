package com.dh.sample.citytoursampleapp.adapter.out.persistence.dto

import java.time.OffsetDateTime

interface OnTourCity {
    fun getStartDate(): OffsetDateTime
    fun getEndDate(): OffsetDateTime
    fun getCityId(): Long
    fun getCityName(): String

}