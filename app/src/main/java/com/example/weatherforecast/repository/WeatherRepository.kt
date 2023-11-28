package com.example.weatherforecast.repository

import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.utils.Response

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): Response<Weather>
}