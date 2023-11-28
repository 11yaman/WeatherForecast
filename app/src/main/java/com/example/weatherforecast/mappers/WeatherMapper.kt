package com.example.weatherforecast.mappers

import com.example.weatherforecast.network.dto.WeatherDto
import com.example.weatherforecast.model.HourlyWeather
import com.example.weatherforecast.model.Weather
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun WeatherDto.toWeather(): Weather {
    val hourlyWeatherMap = hourlyWeather.time.mapIndexed { index, timeString ->
        val time = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_DATE_TIME)
        val temperature = hourlyWeather.temperature[index]
        val cloudCoverage = hourlyWeather.cloudCoverage[index]
        time to HourlyWeather(time, temperature, cloudCoverage)
    }.toMap()

    val currentWeather = hourlyWeatherMap[LocalDateTime.now()]
    return Weather(latitude, longitude, hourlyWeatherMap, currentWeather)
}