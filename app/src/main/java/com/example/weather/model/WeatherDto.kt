package com.example.weather.model

import com.squareup.moshi.Json
import kotlinx.serialization.SerialName

data class WeatherDto(
    @field:Json(name="hourly")
    val weatherData: WeatherDataDto,
)
