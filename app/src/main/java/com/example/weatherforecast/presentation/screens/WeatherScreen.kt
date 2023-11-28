package com.example.weatherforecast.presentation.screens

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.presentation.viewmodels.WeatherViewModel
import com.example.weatherforecast.utils.Response

@Composable
fun WeatherScreen(navController: NavController, viewModel: WeatherViewModel = hiltViewModel()) {

    ShowData(viewModel)
}

@Composable
fun ShowData(viewModel: WeatherViewModel) {
    val weatherData = produceState<Response<Weather>>(
        initialValue = Response.Loading()
    ) {
        value = viewModel.getWeatherData("")
    }.value

    when(weatherData) {
        is Response.Loading ->
            CircularProgressIndicator()
        is Response.Success -> Text("Weather ${weatherData.data}")
        is Response.Error -> Text("Error ")
    }
}
