package com.example.weather.domain

data class WeatherInfo(
    val weatherDataPerDay:Map<Int,List<WeatherData>>,
    val currentWeatherData:WeatherData?
)
