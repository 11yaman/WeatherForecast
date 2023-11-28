package com.example.weatherforecast.utils

sealed class Response<T>(val data: T? = null, val message: String? = null, private val loading: Boolean = false) {
    class Success<T>(data: T?): Response<T>(data, loading = false)
    class Loading<T>: Response<T>(loading = true)
    class Error<T>(message: String, data: T? = null): Response<T>(data, message, loading = false)
}