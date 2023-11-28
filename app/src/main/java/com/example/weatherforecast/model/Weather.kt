package com.example.weatherforecast.model

import android.location.Location
import java.time.LocalDateTime

data class Weather(
    val latitude: Double,
    val longitude: Double,
    val hourlyWeather: Map<LocalDateTime, HourlyWeather>,
    val currentWeather: HourlyWeather?
)