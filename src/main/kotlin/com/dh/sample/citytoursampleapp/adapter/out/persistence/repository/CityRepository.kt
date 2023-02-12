package com.dh.sample.citytoursampleapp.adapter.out.persistence.repository

import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCity
import org.springframework.data.jpa.repository.JpaRepository

interface CityRepository : JpaRepository<EntityCity, Long> {
    fun findByCityNameEquals(cityName: String): EntityCity?
}