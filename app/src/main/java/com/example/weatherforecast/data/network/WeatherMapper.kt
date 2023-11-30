package com.example.weatherforecast.data.network

import android.util.Log
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.data.network.dto.WeatherDto
import com.example.weatherforecast.domain.model.HourlyWeather
import com.example.weatherforecast.domain.model.Weather
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


fun WeatherDto.toWeather(): Weather {
    Log.d("Mapper", "toWeather: inside")
    val hourlyWeatherMap = hourlyWeather.time.mapIndexed { index, timeString ->
        val time = LocalDateTime.parse(timeString, DateTimeFormatter.ISO_DATE_TIME)
        val temperature = hourlyWeather.temperature[index]
        val cloudCoverage = hourlyWeather.cloudCoverage[index]
        time to HourlyWeather(time, temperature, cloudCoverage)
    }.toMap().toSortedMap()

    val truncatedDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).plusHours(1)
    Log.d("Time", "toWeather: $truncatedDateTime")
    val currentWeather = hourlyWeatherMap[truncatedDateTime]

    val place = Place(
        latitude = latitude.toFloat(),
        longitude = longitude.toFloat()
    )
    
    val dailyWeatherMap = hourlyWeatherMap.values.groupBy { entry ->
        val dayNumber = ChronoUnit.DAYS.between(truncatedDateTime, entry.time).toInt()
        dayNumber
    }
    Log.d("TAG", "toWeather: $truncatedDateTime")

    return Weather(place, hourlyWeatherMap, dailyWeatherMap, currentWeather)
}