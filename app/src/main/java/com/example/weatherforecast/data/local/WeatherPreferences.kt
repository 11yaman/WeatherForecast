package com.example.weatherforecast.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherforecast.domain.model.Weather
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeatherPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("WeatherPreferences", Context.MODE_PRIVATE)
    private val gson: Gson

    init {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeTypeAdapter())
        gson = gsonBuilder.create()
    }

    fun saveRecentWeatherData(weather: Weather) {
        val jsonWeather = gson.toJson(weather)
        sharedPreferences.edit().putString("recentWeather", jsonWeather).apply()
    }

    fun getRecentWeatherData(): Weather? {
        val jsonWeather = sharedPreferences.getString("recentWeather", null)
        return if (jsonWeather != null) {
            gson.fromJson(jsonWeather, Weather::class.java)
        } else {
            null
        }
    }

    private class LocalDateTimeTypeAdapter : com.google.gson.JsonSerializer<LocalDateTime>,
        com.google.gson.JsonDeserializer<LocalDateTime> {

        override fun serialize(
            src: LocalDateTime?,
            typeOfSrc: Type?,
            context: com.google.gson.JsonSerializationContext?
        ): com.google.gson.JsonElement {
            return com.google.gson.JsonPrimitive(src?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
        }

        override fun deserialize(
            json: com.google.gson.JsonElement?,
            typeOfT: Type?,
            context: com.google.gson.JsonDeserializationContext?
        ): LocalDateTime {
            return LocalDateTime.parse(json?.asString, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }
    }
}
