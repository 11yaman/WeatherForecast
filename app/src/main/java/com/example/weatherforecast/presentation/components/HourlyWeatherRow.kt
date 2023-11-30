package com.example.weatherforecast.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.domain.model.HourlyWeather
import com.example.weatherforecast.presentation.widgets.WeatherImage
import com.example.weatherforecast.utils.showToast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt


@Composable
fun HourlyWeatherRow(hourlyWeather: Map<LocalDateTime, HourlyWeather>) {
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(2.dp),
        contentPadding = PaddingValues(4.dp)
    ){
        items(hourlyWeather.keys.filter { it.isAfter(LocalDateTime.now()) }
            .take(12)) { hour ->
            val hourWeather = hourlyWeather[hour]
            hourWeather?.let {
                HourWeatherRow(hourWeather = it)
            } ?: run {
                showToast(LocalContext.current, "Couldn't retrieve weather data")
            }
        }
    }
}

@Composable
fun HourWeatherRow(hourWeather: HourlyWeather) {
    val formattedTime = hourWeather.time
        .format(DateTimeFormatter.ofPattern("HH:mm"))

    Column(
        modifier = Modifier
            .height(130.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = formattedTime)
        WeatherImage(currentWeather = hourWeather, Modifier.size(50.dp).padding(2.dp))
        Text(
            text = "${hourWeather.temperature.roundToInt()}°C",
            fontWeight = FontWeight.Bold
        )
    }
}
