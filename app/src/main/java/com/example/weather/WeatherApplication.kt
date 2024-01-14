package com.example.weather

import android.app.Application
import com.example.weather.data.Container
import com.example.weather.data.DefaultAppContainer
import com.example.weather.location.DefaultLocationTracker
import com.example.weather.location.LocationTracker
import com.google.android.gms.location.LocationServices

class WeatherApplication:Application() {
     lateinit var container: Container
     lateinit var defaultLocationTracker: LocationTracker
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        defaultLocationTracker = DefaultLocationTracker(
            LocationServices.getFusedLocationProviderClient(this),
            application = this
        )
    }
}