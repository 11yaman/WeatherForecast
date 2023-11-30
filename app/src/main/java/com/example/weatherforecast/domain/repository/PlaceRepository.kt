package com.example.weatherforecast.domain.repository

import com.example.weatherforecast.data.network.dto.PlaceDto
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.utils.Response

interface PlaceRepository {
    suspend fun getPlacesByName(name: String): Response<List<Place>>
}
