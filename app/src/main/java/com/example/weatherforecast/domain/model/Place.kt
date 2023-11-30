package com.example.weatherforecast.domain.model

data class Place(
    val latitude: Float,
    val longitude: Float,
    val displayName: String ?= null,
    val name: String? = null,
    val country: String? = null,
)