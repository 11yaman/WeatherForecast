package com.example.weatherforecast.domain.model

import java.time.LocalDateTime

data class HourlyWeather(
    val time: LocalDateTime,
    val temperature: Double,
    val cloudCoverage: Int
)