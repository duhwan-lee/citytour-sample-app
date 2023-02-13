package com.dh.sample.citytoursampleapp.adapter.out.persistence.repository

import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.OnTourCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.PlannedCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CityRepository : JpaRepository<EntityCity, Long> {
    fun findByCityNameEquals(cityName: String): EntityCity?

    @Query("SELECT tour.startDate as startDate, tour.endDate as endDate, city.cityId as cityId, city.cityName as cityName " +
            "FROM EntityTour tour LEFT JOIN EntityCity city ON city.cityId = tour.cityId " +
            "WHERE tour.userId = :userId AND tour.startDate <= now() AND tour.endDate >= now()")
    fun findOnTourCityByUserId(userId: Long): List<OnTourCity>

    @Query("SELECT tour.startDate as startDate, city.cityId as cityId, city.cityName as cityName " +
            "FROM EntityTour tour LEFT JOIN EntityCity city ON city.cityId = tour.cityId " +
            "WHERE tour.userId = :userId AND tour.startDate > now()")
    fun findPlannedCityByUserId(userId: Long): List<PlannedCity>
}