package com.example.weather.utils

import com.example.weather.domain.WeatherData
import com.example.weather.domain.WeatherInfo
import com.example.weather.model.WeatherDataDto
import com.example.weather.model.WeatherDto
import com.example.weather.resources.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class IndexedWeatherData(
    val index:Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap():Map<Int,List<WeatherData>>{
    return time.mapIndexed{index,time->
        val temperature = temperature[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidity[index]
     IndexedWeatherData(
         index = index,
         data =    WeatherData(
             time= LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
             temperatureCelsius = temperature,
             windSpeed = windSpeed,
             humidity = humidity,
             pressure = pressure,
             weatherType = WeatherType.fromWMO(weatherCode)
         )
     )
    }.groupBy {
        it.index/24
    }.mapValues {
        it.value.map {
            it.data
        }
    }
}

fun WeatherDto.toWeatherInfo():WeatherInfo{
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute > 30) now.hour+1 else now.hour
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}