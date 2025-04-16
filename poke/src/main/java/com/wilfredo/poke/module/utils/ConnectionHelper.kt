package com.wilfredo.poke.module.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectionHelper(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun hasInternet(): Boolean {
        with(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)) {
            if (this == null) {
                return false
            }
            return hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        }
    }

    companion object {
        private var INSTANCE: ConnectionHelper? = null
        fun getInstance(context: Context): ConnectionHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = ConnectionHelper(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }
}