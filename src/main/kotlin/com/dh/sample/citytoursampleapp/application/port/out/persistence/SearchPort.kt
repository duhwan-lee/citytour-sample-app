package com.dh.sample.citytoursampleapp.application.port.out.persistence

import org.springframework.scheduling.annotation.Async

interface SearchPort {
    @Async
    fun saveSearchInfo(cityId: Long)
}