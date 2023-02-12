package com.dh.sample.citytoursampleapp.domain.exception

enum class ErrorType(val defaultMsg: String) {
    DUPLICATED_CITY_NAME("이미 등록된 도시입니다."),
    NOT_EXIST_CITY("등록되지 않은 도시입니다.");

}