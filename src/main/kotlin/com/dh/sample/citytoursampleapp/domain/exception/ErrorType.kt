package com.dh.sample.citytoursampleapp.domain.exception

enum class ErrorType(val defaultMsg: String) {
    INVALID_DATE_FORMAT("유효한 날짜 형식은 yyyy-MM-dd 입니다."),
    SOONER_THAN_START_DATE("종료일이 시작일보다 더 빠를 수 없습니다."),
    DUPLICATED_CITY_NAME("이미 등록된 도시입니다."),
    NOT_EXIST_CITY("등록되지 않은 도시입니다."),
    NOT_EXIST_USER("존재하지 않는 유저입니다."),
    NOT_EXIST_TOUR("존재하지 않는 여행입니다.");

}