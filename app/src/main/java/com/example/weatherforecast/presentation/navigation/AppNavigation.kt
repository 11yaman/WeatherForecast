package com.example.weatherforecast.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecast.presentation.screens.WeatherScreen
import com.example.weatherforecast.presentation.viewmodels.WeatherViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.WeatherScreen.name) {
        composable(AppScreens.WeatherScreen.name) {
            val weatherViewModel = hiltViewModel<WeatherViewModel>()
            WeatherScreen(navController = navController, viewModel = weatherViewModel)
        }
    }
}