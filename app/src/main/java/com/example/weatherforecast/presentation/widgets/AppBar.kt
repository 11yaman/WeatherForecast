package com.example.weatherforecast.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.presentation.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    //navController: NavController,
    currentScreen: AppScreens,
    onSearchClick: () -> Unit = {},
    onBackClicked: () -> Unit = {}
    ) {
    TopAppBar(
        title = {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
        },
        navigationIcon = {
            if(currentScreen != AppScreens.WeatherScreen) {
                BackButton(onBackClicked)
            }
        },
        actions = {
            if(currentScreen == AppScreens.WeatherScreen) {
                    SearchButton(onSearchClick)
                    Spacer(modifier = Modifier.width(4.dp))
                    SettingsMenu()
            }
        }
    )
}

@Composable
private fun BackButton(onBackClicked: () -> Unit) {
    IconButton(onClick = onBackClicked) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
    }
}

@Composable
private fun SearchButton(onSearchClick: () -> Unit) {
    IconButton(onClick = onSearchClick) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
    }
}

@Composable
fun SettingsMenu() {
    var expanded by remember { mutableStateOf(false) }
    val iconModifier = Modifier.size(24.dp)

    IconButton(
        onClick = { expanded = !expanded },
        modifier = iconModifier
    ) {
        Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "Settings")
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        DropdownMenuItem(
            onClick = {
                expanded = false
            },
            modifier = Modifier,
            text = {
                Text(text = "Favorites")
            }
        )
        DropdownMenuItem(
            onClick = {
            },
            modifier = Modifier,
            text = {
                expanded = false
                Text(text = "About")
            }
        )
    }
}
@Preview
@Composable
fun AppBarPreview() {

    AppBar(
        title = "Place Name",
        currentScreen = AppScreens.WeatherScreen,
        onSearchClick = {},
        onBackClicked = {}
    )
}