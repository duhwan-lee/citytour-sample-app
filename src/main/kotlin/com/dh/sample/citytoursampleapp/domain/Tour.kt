package com.dh.sample.citytoursampleapp.domain

import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityTour
import java.time.OffsetDateTime

data class Tour(
    val tourId: Long,
    val userId: Long,
    val city: City,
    val startDate: OffsetDateTime,
    val endDate: OffsetDateTime
) {
    companion object {
        fun from(entityTour: EntityTour, entityCity: EntityCity): Tour {
            return Tour(
                entityTour.tourId!!,
                entityTour.userId,
                City.from(entityCity),
                entityTour.startDate,
                entityTour.endDate
            )
        }
    }
}