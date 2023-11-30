package com.example.weatherforecast.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.R
import com.example.weatherforecast.domain.model.HourlyWeather
import java.time.LocalDateTime

@Composable
fun WeatherImage(currentWeather: HourlyWeather, modifier: Modifier) {
    val weatherImageRes = when {
        currentWeather.cloudCoverage < 40 -> R.drawable.ic_sunny
        currentWeather.cloudCoverage < 90 && isDaytime(currentWeather.time) -> R.drawable.ic_partly_cloudy_day
        currentWeather.cloudCoverage < 90 && !isDaytime(currentWeather.time) -> R.drawable.ic_partly_cloudy_night
        else -> R.drawable.ic_cloudy
    }

    Image(
        painter = painterResource(id = weatherImageRes),
        contentDescription = "Weather image",
        modifier = modifier
    )
}

private fun isDaytime(time: LocalDateTime): Boolean {
    val hour = time.hour
    return hour in 6..18
}