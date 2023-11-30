package com.example.weatherforecast.presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.domain.model.Weather
import com.example.weatherforecast.presentation.components.DailyWeatherList
import com.example.weatherforecast.presentation.components.WeatherCard
import com.example.weatherforecast.presentation.components.HourlyWeatherRow
import com.example.weatherforecast.presentation.navigation.AppScreens
import com.example.weatherforecast.presentation.viewmodels.WeatherViewModel
import com.example.weatherforecast.presentation.widgets.AppBar
import com.example.weatherforecast.utils.Response
import com.example.weatherforecast.utils.Response.*
import com.example.weatherforecast.utils.showToast

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel(),
    place: Place
) {
    Log.d("TAG", "WeatherScreen: $place")
    val result = produceState<Response<Weather>>(
        initialValue = Loading()
    ) {
        value = viewModel.getWeatherData(place)
    }.value

    when (result) {
        is Loading -> CircularProgressIndicator()
        is Success -> result.data?.let {
            WeatherScaffold(it, navController, place)
        } ?: showToast(LocalContext.current, "Couldn't retrieve weather data")
        is Error -> showToast(LocalContext.current, "Couldn't retrieve weather data")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScaffold(weather: Weather, navController: NavController, place: Place) {
    Scaffold(topBar = {
        AppBar(
            title = if (!place.name.isNullOrEmpty())
                place.name + ", " + place.country
                else "",
            currentScreen = AppScreens.WeatherScreen,
            onSearchClick = {
                navController.navigate(AppScreens.SearchScreen.name)
            }
        )
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(2.dp)
        ) {
            item {
                WeatherCard(weather)
            }
            item {
                HourlyWeatherRow(weather.hourlyWeather)
            }
            item {
                DailyWeatherList(weather.dailyWeather)
            }
        }
    }
}
