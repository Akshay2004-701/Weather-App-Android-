package com.example.weather.data

import com.example.weather.apiService.WeatherApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface Container {
    val repository:Repository
}

class DefaultAppContainer:Container{

    private val baseURL = "https://api.open-meteo.com/"

    private val retrofit:Retrofit =
        Retrofit.Builder().baseUrl(baseURL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val retrofitService:WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    override val repository: Repository by lazy {
        WeatherRepository(retrofitService)
    }
}