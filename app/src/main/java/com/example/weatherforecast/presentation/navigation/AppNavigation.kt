package com.example.weatherforecast.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.presentation.screens.SearchScreen
import com.example.weatherforecast.presentation.screens.WeatherScreen
import com.example.weatherforecast.presentation.viewmodels.SearchViewModel
import com.example.weatherforecast.presentation.viewmodels.WeatherViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val weatherRoute = "${AppScreens.WeatherScreen.name}?latitude={latitude}&longitude={longitude}&name={name}&country={country}"

    NavHost(
        navController = navController,
        startDestination = weatherRoute
    ) {
        composable(route = weatherRoute,
            arguments = listOf(
                navArgument(name = "latitude") {
                    type = NavType.FloatType
                    defaultValue = 59.3294f
                },
                navArgument(name = "longitude") {
                    type = NavType.FloatType
                    defaultValue = 18.0687f
                },
                navArgument(name = "name") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(name = "country") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val lat = it.arguments?.getFloat("latitude") ?: 59.3294f// TODO: Change To recent place
            val long = it.arguments?.getFloat("longitude") ?: 18.0687f
            val name = it.arguments?.getString("name")
            val country = it.arguments?.getString("country")

            val weatherViewModel = hiltViewModel<WeatherViewModel>()
            WeatherScreen(
                navController = navController,
                viewModel = weatherViewModel,
                place = Place(latitude = lat, longitude = long, name = name, country = country)
            )
        }

        composable(AppScreens.SearchScreen.name) {
            val searchViewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(navController = navController, searchViewModel)
        }
    }
}