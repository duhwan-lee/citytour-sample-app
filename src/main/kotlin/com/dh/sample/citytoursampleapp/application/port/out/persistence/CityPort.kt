package com.dh.sample.citytoursampleapp.application.port.out.persistence

import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCity

interface CityPort {
    fun findByCityName(cityName: String): EntityCity?

    fun insertEntitySave(entityCity: EntityCity): EntityCity

    fun findByCityId(id: Long): EntityCity?

    fun updateEntitySave(entityCity: EntityCity): EntityCity
}