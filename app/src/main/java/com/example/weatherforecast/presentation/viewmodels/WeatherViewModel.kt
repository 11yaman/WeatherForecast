package com.example.weatherforecast.presentation.viewmodels

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.data.local.WeatherPreferences
import com.example.weatherforecast.domain.model.HourlyWeather
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.domain.model.Weather
import com.example.weatherforecast.domain.repository.WeatherRepository
import com.example.weatherforecast.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val preferences: WeatherPreferences
) : ViewModel(){

    suspend fun getWeatherForPlace(place: Place): Response<Weather> {
        val res = repository.getWeather(place)
        if(res is Response.Success) res.data?.let { preferences.saveRecentWeatherData(it) }
        return res
    }

    suspend fun getRecentWeather(): Response<Weather> {
        val result : Response<Weather> = try {
            var data =  preferences.getRecentWeatherData()

            val lastAvailableDate = data?.dailyWeather?.values?.lastOrNull()?.lastOrNull()?.time?.toLocalDate()

            if (LocalDate.now().plusDays(7).isAfter(lastAvailableDate)){
                val newData = data?.place?.let { getWeatherForPlace(it)}
                data = newData?.data ?: data
            }


            val currentWeather = data?.hourlyWeather?.get(
                LocalDateTime.now().withMinute(0).withSecond(0).withNano(0)
            )
            Response.Success(data = data?.let { Weather(it.place, it.hourlyWeather, it.dailyWeather, currentWeather) })
        } catch (e: Exception) {
            Response.Error("Error retrieving recent data")
        }
        return result
    }
}