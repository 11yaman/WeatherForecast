package com.example.weatherforecast.di

import com.example.weatherforecast.data.local.WeatherPreferences
import android.content.Context
import com.example.weatherforecast.data.network.GeocodingAPI
import com.example.weatherforecast.data.network.WeatherForecastApi
import com.example.weatherforecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideWeatherForecastApi(): WeatherForecastApi {
        return Retrofit.Builder()
            .baseUrl(Constants.WEATHER_FORECAST_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherForecastApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeocodingApi(): GeocodingAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.GEOCODING_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeocodingAPI::class.java)
    }
    @Provides
    @Singleton
    fun provideWeatherPreferences(@ApplicationContext context: Context):
            WeatherPreferences {
        return WeatherPreferences(context)
    }
}