package com.example.weatherforecast.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.data.local.WeatherPreferences
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.domain.model.Weather
import com.example.weatherforecast.domain.repository.WeatherRepository
import com.example.weatherforecast.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val preferences: WeatherPreferences
) : ViewModel(){

    suspend fun getWeatherData(place: Place): Response<Weather> {
        return repository.getWeather(place)
    }

    fun saveRecentWeather(weather: Weather) {
        viewModelScope.launch {
            preferences.saveRecentWeather(weather)
        }
    }

    suspend fun getRecentWeather(): Response<Weather> {
        val result : Response<Weather> = try {
            Response.Success(data = preferences.getRecentWeather())
        } catch (e: Exception) {
            Response.Error("Error retrieving recent data")
        }
        return result
    }
}