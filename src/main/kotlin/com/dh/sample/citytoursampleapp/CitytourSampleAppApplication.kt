package com.dh.sample.citytoursampleapp

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync(proxyTargetClass = true)
@SpringBootApplication
class CitytourSampleAppApplication

inline fun <reified T> logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

fun main(args: Array<String>) {
    runApplication<CitytourSampleAppApplication>(*args)
}
