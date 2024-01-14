package com.example.weather.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation():Location?
}