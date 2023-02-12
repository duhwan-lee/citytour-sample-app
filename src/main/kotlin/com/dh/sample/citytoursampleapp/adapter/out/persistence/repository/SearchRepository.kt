package com.dh.sample.citytoursampleapp.adapter.out.persistence.repository

import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCitySearch
import org.springframework.data.jpa.repository.JpaRepository

interface SearchRepository : JpaRepository<EntityCitySearch, Long> {
}