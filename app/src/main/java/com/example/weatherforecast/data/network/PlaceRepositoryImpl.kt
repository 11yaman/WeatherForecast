package com.example.weatherforecast.data.network

import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.domain.repository.PlaceRepository
import com.example.weatherforecast.utils.Response
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(private val api: GeocodingAPI):
    PlaceRepository {
    override suspend fun getPlacesByName(name: String): Response<List<Place>> {
        val response = try {
            val places = api.getPlacesByName(name)
            Response.Success(
                data = places.map {
                    val displayNameParts = it.name.split(", ")
                    val place = displayNameParts.first()
                    val country = displayNameParts.last()
                    Place(
                        latitude = it.lat,
                        longitude = it.lon,
                        displayName = it.name,
                        name = place,
                        country = country
                    )
                }
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Response.Error("Could not retrieve places")
        }
        return response;
    }
}
