package com.example.weatherforecast.data.network.dto

import com.google.gson.annotations.SerializedName

data class HourlyWeatherDto(
    @SerializedName("cloud_cover")
    val cloudCoverage: List<Int>,

    @SerializedName("temperature_2m")
    val temperature: List<Double>,

    val time: List<String>
)