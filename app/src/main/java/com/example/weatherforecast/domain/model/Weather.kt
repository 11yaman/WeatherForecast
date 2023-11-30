package com.example.weatherforecast.domain.model

import java.time.LocalDateTime

data class Weather(
    val place: Place,
    val hourlyWeather: Map<LocalDateTime, HourlyWeather>,
    val dailyWeather: Map<Int, List<HourlyWeather>>,
    val currentWeather: HourlyWeather?
)