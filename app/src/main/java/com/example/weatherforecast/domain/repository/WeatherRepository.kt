package com.example.weatherforecast.domain.repository

import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.domain.model.Weather
import com.example.weatherforecast.utils.Response

interface WeatherRepository {
    suspend fun getWeather(place: Place): Response<Weather>
}