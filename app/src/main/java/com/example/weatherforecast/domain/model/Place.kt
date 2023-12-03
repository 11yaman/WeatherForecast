package com.example.weatherforecast.domain.model

data class Place(
    val latitude: Float,
    val longitude: Float,
    val name: String,
    val country: String,
    val displayName: String? = null,
)