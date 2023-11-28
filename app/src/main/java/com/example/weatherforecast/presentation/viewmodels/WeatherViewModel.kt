package com.example.weatherforecast.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.repository.WeatherRepository
import com.example.weatherforecast.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository)
    : ViewModel(){

    suspend fun getWeatherData(city: String): Response<Weather> {
        //TODO: Change to retrieve location by city name
        val response = repository.getWeather(latitude = 59.3294, longitude = 18.0687)
        Log.d("ViewModel", "getWeatherData: $response")
        return response
    }
}