package com.dh.sample.citytoursampleapp.adapter.out.persistence.repository

import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityTour
import org.springframework.data.jpa.repository.JpaRepository

interface TourRepository : JpaRepository<EntityTour, Long> {
    fun findByUserIdEquals(userId: Long): List<EntityTour>
    fun findByCityIdEquals(cityId: Long): List<EntityTour>

}