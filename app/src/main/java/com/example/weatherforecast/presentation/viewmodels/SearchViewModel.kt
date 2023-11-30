package com.example.weatherforecast.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.produceState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.domain.repository.PlaceRepository
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.domain.model.Weather
import com.example.weatherforecast.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val placeRepository: PlaceRepository
) : ViewModel() {
    private val _placeListResult = MutableStateFlow<Response<List<Place>>>(Response.Success(data = emptyList()))
    val placeListResult: StateFlow<Response<List<Place>>>
        get() = _placeListResult

    fun searchPlacesByName(placeName: String){
        viewModelScope.launch {
            try {
                _placeListResult.value = Response.Loading()
                _placeListResult.value = placeRepository.getPlacesByName(placeName)
            } catch (e: Exception) {
                _placeListResult.value = Response.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}