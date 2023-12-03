package com.example.weatherforecast.presentation.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecast.domain.model.Place
import com.example.weatherforecast.presentation.navigation.AppScreens
import com.example.weatherforecast.presentation.viewmodels.SearchViewModel
import com.example.weatherforecast.presentation.widgets.AppBar
import com.example.weatherforecast.presentation.widgets.LoadingIndicator
import com.example.weatherforecast.utils.Response
import com.example.weatherforecast.utils.showToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    val placeListResult by viewModel.placeListResult.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                title = "Search",
                currentScreen = AppScreens.SearchScreen,
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                label = { Text("Place") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.searchPlacesByName(searchQuery)
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            when (placeListResult) {
                is Response.Loading -> LoadingIndicator(Modifier)
                is Response.Success -> placeListResult.data
                    ?.let {
                        PlaceList(it, navController)
                    } ?: showToast(LocalContext.current,
                    "Couldn't retrieve places")
                is Response.Error ->
                    showToast(LocalContext.current,
                        placeListResult.message ?: "Couldn't retrieve data")
            }
        }
    }
}

@Composable
fun PlaceList(places: List<Place>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(places) { place ->
            place.displayName?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .clickable {
                            Log.d("TAG", "PlaceList: ${place.latitude}")
                            navController.navigate(
                                "${AppScreens.WeatherScreen.name}?latitude=${place.latitude}&longitude=${place.longitude}" +
                                        "&name=${place.name}&country=${place.country}"
                            )
                        }
                )
            }
        }
    }
}
