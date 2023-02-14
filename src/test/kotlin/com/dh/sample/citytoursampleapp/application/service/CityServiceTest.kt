package com.dh.sample.citytoursampleapp.application.service

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class CityServiceTest {

    @Test
    fun testIsWithIn24H() {
        val now = LocalDateTime.now()
        val target1 = now.minusHours(23).minusMinutes(59)
        val target2 = now.minusHours(24)
        val target3 = now.plusSeconds(1)
        assertTrue(CityService.isWithIn24H(target1, now))
        assertFalse(CityService.isWithIn24H(target2, now))
        assertFalse(CityService.isWithIn24H(target3, now))
    }
}