package com.dh.sample.citytoursampleapp.adapter.out.persistence.entity

import jakarta.persistence.*
import java.time.OffsetDateTime


@Entity
@Table(name = "city_search")
class EntityCitySearch(cityId: Long) {
    @Id
    val cityId: Long = cityId

    val searchAt: OffsetDateTime = OffsetDateTime.now()
}