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
    @GeneratedValue(strategy = GenerationType.AUTO)
    val cityId: Long? = null

    var cityName: String = cityName
        private set

    @CreatedDate
    var createAt: LocalDateTime = LocalDateTime.now()
        private set

    fun modify(cityName: String) {
        this.cityName = cityName
    }
}