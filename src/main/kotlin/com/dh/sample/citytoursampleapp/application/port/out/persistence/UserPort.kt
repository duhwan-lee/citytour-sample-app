package com.dh.sample.citytoursampleapp.application.port.out.persistence

interface UserPort {

    fun isExistUser(userId: Long): Boolean
}