package com.example.weatherforecast.di

import com.example.weatherforecast.domain.repository.PlaceRepository
import com.example.weatherforecast.data.network.PlaceRepositoryImpl
import com.example.weatherforecast.domain.repository.WeatherRepository
import com.example.weatherforecast.data.network.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindPlaceRepository(
        placeRepository: PlaceRepositoryImpl
    ): PlaceRepository
}