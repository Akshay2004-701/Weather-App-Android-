package com.example.weather.data

import com.example.weather.apiService.WeatherApi
import com.example.weather.domain.WeatherInfo
import com.example.weather.resources.Resource
import com.example.weather.utils.toWeatherInfo

class WeatherRepository(private val weatherApi: WeatherApi):Repository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = weatherApi.getWeatherData(lat,long).toWeatherInfo()
            )
        }
        catch (e:Exception){
            e.printStackTrace()
            Resource.Error(e.message?:"Unknown error occurred")
        }
    }
}