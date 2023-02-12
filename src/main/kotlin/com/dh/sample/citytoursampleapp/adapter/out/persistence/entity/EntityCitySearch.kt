package com.dh.sample.citytoursampleapp.adapter.out.persistence.entity

import jakarta.persistence.*
import java.time.OffsetDateTime


@Entity
@Table(name = "city_search")
class EntityCitySearch(cityId: Long) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val cityId: Long = cityId

    val searchedAt: OffsetDateTime = OffsetDateTime.now()
}