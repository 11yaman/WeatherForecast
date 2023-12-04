package com.example.weatherforecast.presentation.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.R
import com.example.weatherforecast.domain.model.HourlyWeather
import com.example.weatherforecast.domain.model.Weather
import com.example.weatherforecast.presentation.widgets.WeatherImage
import com.example.weatherforecast.utils.showToast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(data: Weather) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .height(250.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0x7903A9F4)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(data.currentWeather!=null) {
                val formattedDate = data.currentWeather.time
                    .format(DateTimeFormatter.ofPattern("dd MMMM") )

                Text(
                    text = formattedDate,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(16.dp))
                WeatherImage(currentWeather = data.currentWeather, Modifier.size(100.dp).padding(6.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.currentWeather.temperature.roundToInt()}°C",
                    fontSize = 40.sp,
                    color = Color.White
                )
            }
            else {
                showToast(LocalContext.current, "Could not retrieve current weather")
            }
        }
    }
}
