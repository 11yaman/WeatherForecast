package com.example.weatherforecast.data.network.dto

import com.google.gson.annotations.SerializedName

data class PlaceDto (
    val lat: Float,
    val lon: Float,
    @SerializedName("display_name")
    val name: String
)
