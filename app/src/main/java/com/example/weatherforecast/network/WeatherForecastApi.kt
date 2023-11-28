package com.example.weatherforecast.network

import com.example.weatherforecast.network.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherForecastApi {

    @GET(value = "v1/forecast?hourly=temperature_2m,cloud_cover&timezone=auto")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ) : WeatherDto
}