package com.example.weatherforecast.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.domain.model.Weather
import com.example.weatherforecast.domain.repository.WeatherRepository
import com.example.weatherforecast.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel(){

    suspend fun getWeatherData(place: Place): Response<Weather> {
        return repository.getWeather(place)
    }
}