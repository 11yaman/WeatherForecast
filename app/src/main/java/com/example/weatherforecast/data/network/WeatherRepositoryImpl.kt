package com.example.weatherforecast.data.network

import android.util.Log
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.domain.model.Weather
import com.example.weatherforecast.domain.repository.WeatherRepository
import com.example.weatherforecast.utils.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherForecastApi):
    WeatherRepository {

     override suspend fun getWeather(place: Place): Response<Weather> {
        val response = try {
            Response.Success(
                data = api.getWeatherData(
                    latitude = place.latitude.toDouble(),
                    longitude = place.longitude.toDouble()
                ).toWeather(place)
            )
        } catch(e: Exception) {
            Response.Error("Could not retrieve weather data")
        }
         return response;
    }
}