package com.example.weatherforecast.data.network

import com.example.weatherforecast.data.network.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherForecastApi {

    @GET(value = "v1/forecast?hourly=temperature_2m,cloud_cover&timezone=Europe%2FBerlin")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ) : WeatherDto
}