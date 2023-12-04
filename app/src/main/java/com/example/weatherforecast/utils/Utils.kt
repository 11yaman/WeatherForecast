package com.example.weatherforecast.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun isConnectedToNetwork(context: Context): Boolean {
    val cmg = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    cmg.getNetworkCapabilities(cmg.activeNetwork)?.let { networkCapabilities ->
        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }

    return false
}
