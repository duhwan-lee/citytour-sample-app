package com.dh.sample.citytoursampleapp.adapter.out.persistence.entity

import jakarta.persistence.*
import java.time.OffsetDateTime


@Entity
@Table(name = "tour")
class EntityTour(
    userId: Long,
    cityId: Long,
    startDateTime: OffsetDateTime,
    endDateTime: OffsetDateTime
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val tourId: Long? = null

    val userId: Long = userId
    var cityId: Long = cityId
        private set
    var startDate: OffsetDateTime = startDateTime
        private set
    var endDate: OffsetDateTime = endDateTime
        private set

    fun update(cityId: Long, startDateTime: OffsetDateTime, endDateTime: OffsetDateTime) {
        this.cityId = cityId
        this.startDate = startDateTime
        this.endDate = endDateTime
    }
}