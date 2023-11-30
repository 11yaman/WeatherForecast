package com.example.weatherforecast.data.network

import com.example.weatherforecast.data.network.dto.PlaceDto
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface GeocodingAPI {
    @GET("search")
    suspend fun getPlacesByName(@Query("q") name: String): List<PlaceDto>
}