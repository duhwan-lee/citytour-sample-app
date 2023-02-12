package com.dh.sample.citytoursampleapp.adapter.out.persistence.entity

import jakarta.persistence.*


@Entity
@Table(name = "city")
class EntityCity(cityName: String, country: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val cityId: Long? = null

    val country: String = country
    var cityName: String = cityName
        private set

    fun modify(cityName: String) {
        this.cityName = cityName
    }
}