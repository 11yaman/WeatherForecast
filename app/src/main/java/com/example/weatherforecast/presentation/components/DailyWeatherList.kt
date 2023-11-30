package com.example.weatherforecast.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.domain.model.HourlyWeather
import com.example.weatherforecast.presentation.widgets.WeatherImage
import com.example.weatherforecast.utils.showToast
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun DailyWeatherList(dailyWeather: Map<Int, List<HourlyWeather>>) {
    Surface(
        modifier =  Modifier.fillMaxSize(),
        shape = RoundedCornerShape(12.dp),
        color = Color(0x1B03A9F4)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            content = {
                dailyWeather.keys.drop(1).forEach { day ->
                    val dayWeather = dailyWeather[day]
                    dayWeather?.let {
                        DayWeatherRow(dayWeather)
                    } ?: run {
                        showToast(LocalContext.current, "Couldn't retrieve weather data")
                    }
                }
            }
        )
    }
}

@Composable
fun DayWeatherRow(dayWeather: List<HourlyWeather>) {
    Surface(
        Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            val formattedDayName = dayWeather.last().time.format(DateTimeFormatter.ofPattern("EEE"))
            Text(
                text = formattedDayName,
                modifier = Modifier.padding(start = 10.dp)
            )

            val weatherForImage = dayWeather.maxBy { it.cloudCoverage }
            WeatherImage(
                currentWeather =
                    HourlyWeather(
                        time = LocalDateTime.of(weatherForImage.time.toLocalDate(),
                        LocalTime.of(12,0)),
                        temperature = weatherForImage.temperature,
                        cloudCoverage = weatherForImage.cloudCoverage
                    ),
                modifier = Modifier.size(70.dp).padding(2.dp)
            )

            val maxTemperature = dayWeather.maxByOrNull { it.temperature }?.temperature
            val minTemperature = dayWeather.minByOrNull { it.temperature }?.temperature

            Column(
                modifier = Modifier.padding(end = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Blue)) {
                            append("${maxTemperature?.roundToInt()} °C")
                        }
                    }
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("${minTemperature?.roundToInt()} °C")
                        }
                    }
                )
            }
        }
    }
}