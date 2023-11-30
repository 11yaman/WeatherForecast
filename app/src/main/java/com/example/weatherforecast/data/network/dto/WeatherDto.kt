package com.example.weatherforecast.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("hourly")
    val hourlyWeather: HourlyWeatherDto,

    val latitude: Double,

    val longitude: Double,
)