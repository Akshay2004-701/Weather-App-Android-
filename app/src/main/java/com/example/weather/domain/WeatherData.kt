package com.example.weather.domain

import com.example.weather.resources.WeatherType
import java.time.LocalDateTime

data class WeatherData(
    val time:LocalDateTime,
    val temperatureCelsius:Double,
    val pressure:Double,
    val windSpeed:Double,
    val humidity:Double,
    val weatherType: WeatherType
)
