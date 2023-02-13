package com.dh.sample.citytoursampleapp.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "city_search")
class EntityCitySearch(cityId: Long) {
    @Id
    val cityId: Long = cityId

    val searchAt: LocalDateTime = LocalDateTime.now()

    fun isWithIn7d(now:LocalDateTime): Boolean {
        return searchAt.plusWeeks(1).isAfter(now) && now.minusWeeks(1).isBefore(searchAt)
    }
}