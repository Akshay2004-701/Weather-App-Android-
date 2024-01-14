package com.example.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.presentation.WeatherCard
import com.example.weather.presentation.WeatherViewModel
import com.example.weather.ui.theme.Purple40
import com.example.weather.ui.theme.Purple80
import com.example.weather.ui.theme.PurpleGrey40

@Composable
fun WeatherApp(
    viewModel: WeatherViewModel = viewModel(factory=WeatherViewModel.Factory)
){
    val state = viewModel.state

    Row(modifier = Modifier.fillMaxSize(),Arrangement.Center) {
        WeatherCard(weatherState = state, backgroundColor = Purple80)
    }

}