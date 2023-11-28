package com.example.weatherforecast.repository.impl

import android.util.Log
import com.example.weatherforecast.mappers.toWeather
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.network.WeatherForecastApi
import com.example.weatherforecast.repository.WeatherRepository
import com.example.weatherforecast.utils.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherForecastApi): WeatherRepository {

     override suspend fun getWeather(latitude: Double, longitude: Double): Response<Weather> {
        val response = try {
            Response.Success(
                data = api.getWeatherData(latitude = latitude, longitude = longitude)
                    .toWeather()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Response.Error(e.message ?: "An unknown error occurred.")
        }
         Log.d("Repository", "getWeather: $response")
         return response;
    }
}