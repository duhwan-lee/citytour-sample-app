package com.dh.sample.citytoursampleapp.domain

import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCity
import java.time.LocalDateTime

/*
* country는 국가값으로 변경할 수 없음.
* */
data class City(
    val cityId: Long,
    val cityName: String,
    val country: String,
    val createdAt: LocalDateTime,
    val searchAt: LocalDateTime?
) {
    companion object {
        fun from(entityCity: EntityCity): City {
            return City(
                entityCity.cityId!!,
                entityCity.cityName,
                entityCity.country,
                entityCity.createAt,
                entityCity.searchAt()
            )
        }
    }
}