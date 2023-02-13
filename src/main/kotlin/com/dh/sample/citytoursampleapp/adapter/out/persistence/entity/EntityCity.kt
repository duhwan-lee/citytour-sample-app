package com.dh.sample.citytoursampleapp.adapter.out.persistence.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@Entity
@Table(name = "city")
@EntityListeners(AuditingEntityListener::class)
class EntityCity(cityName: String, val country: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val cityId: Long? = null

    var cityName: String = cityName
        private set

    @CreatedDate
    var createAt: LocalDateTime = LocalDateTime.now()
        private set

    @OneToOne
    @JoinColumn(name = "city_id")
    private val entityCitySearch: EntityCitySearch? = null
    fun modify(cityName: String) {
        this.cityName = cityName
    }

    fun isWithIn7daySearch(now: LocalDateTime): Boolean {
        return entityCitySearch?.isWithIn7d(now) ?: false
    }

    fun searchAt(): LocalDateTime? {
        return entityCitySearch?.searchAt
    }
}