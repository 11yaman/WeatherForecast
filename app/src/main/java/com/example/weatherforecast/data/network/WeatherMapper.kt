package com.example.weatherforecast.data.network

import android.util.Log
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.data.network.dto.WeatherDto
import com.example.weatherforecast.domain.model.HourlyWeather
import com.example.weatherforecast.domain.model.Weather
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


fun WeatherDto.toWeather(weatherPlace: Place): Weather {
    val hourlyWeatherMap = hourlyWeather.time.mapIndexed { index, timeString ->
        val time = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_DATE_TIME)
        val temperature = hourlyWeather.temperature[index]
        val cloudCoverage = hourlyWeather.cloudCoverage[index]
        time to HourlyWeather(time, temperature, cloudCoverage)
    }.toMap().toSortedMap()

    val truncatedDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)
    val currentWeather = hourlyWeatherMap[truncatedDateTime]

    val place = Place(
        latitude = latitude.toFloat(),
        longitude = longitude.toFloat(),
        name = weatherPlace.name,
        country = weatherPlace.country,
        displayName = weatherPlace.displayName
    )

    val dailyWeatherMap = (0..6).associateWith { day ->
        val startOfDay = truncatedDateTime.plusDays(day.toLong()).with(LocalTime.MIDNIGHT)
        val endOfDay = startOfDay.plusDays(1).minusSeconds(1)
        val hourlyWeatherForDay = hourlyWeatherMap.filter { (time, _) ->
            time.isAfter(startOfDay) && time.isBefore(endOfDay)
        }.values.toList()
        hourlyWeatherForDay
    }.toSortedMap()

    return Weather(place, hourlyWeatherMap, dailyWeatherMap, currentWeather)
}