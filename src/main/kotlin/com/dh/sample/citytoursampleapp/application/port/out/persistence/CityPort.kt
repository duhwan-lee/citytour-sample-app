package com.dh.sample.citytoursampleapp.application.port.out.persistence

import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.OnTourCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.PlannedCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCity

interface CityPort {
    fun findByCityName(cityName: String): EntityCity?

    fun insertEntitySave(entityCity: EntityCity): EntityCity

    fun findByCityId(cityId: Long): EntityCity?

    fun updateEntitySave(entityCity: EntityCity): EntityCity

    fun deleteCityInfo(cityId: Long)

    //현재 여행 진행중인 도시목록
    fun findOnTourCityByUserId(userId: Long): List<OnTourCity>

    //여행 예정 도시목록
    fun findDistinctPlannedCityByUserId(userId: Long): List<PlannedCity>

    fun findAllCities(): List<EntityCity>
}