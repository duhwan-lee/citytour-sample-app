package com.dh.sample.citytoursampleapp.infrastructure

import com.dh.sample.citytoursampleapp.domain.exception.CityTourException
import com.dh.sample.citytoursampleapp.domain.exception.ErrorType
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import java.time.LocalDate
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


class JsonDateDeserializer(vc: Class<*>?) : StdDeserializer<OffsetDateTime>(vc) {

    companion object {
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext?): OffsetDateTime {
        val date: String = p.text
        return try {
            val localDate = LocalDate.parse(date, formatter)
            OffsetDateTime.of(localDate, LocalTime.NOON, ZoneOffset.UTC)
        } catch (e: DateTimeParseException) {
            throw CityTourException(ErrorType.INVALID_DATE_FORMAT)
        }
    }
}