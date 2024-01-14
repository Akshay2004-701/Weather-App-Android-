package com.example.weather.data

import com.example.weather.domain.WeatherInfo
import com.example.weather.resources.Resource

interface Repository {
    suspend fun getWeatherData(lat:Double,long:Double):Resource<WeatherInfo>
}