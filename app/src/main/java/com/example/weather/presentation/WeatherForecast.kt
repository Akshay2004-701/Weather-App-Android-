package com.example.weather.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.domain.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherForecast(
    modifier: Modifier=Modifier,
    weatherState: WeatherState
){
    weatherState.weatherInfo?.weatherDataPerDay?.get(0)?.let {dataList->
        Column(
            modifier= modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
                )
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(content = {
                items(dataList){
                    HourlyComposable(weatherData = it,
                        modifier= Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp))
                }
            })
        }
    }

}

@Composable
fun HourlyComposable(
    weatherData: WeatherData,
    modifier: Modifier=Modifier
){
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = formattedTime, color = Color.Black)
        
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null
        )

        Text(text = "${weatherData.temperatureCelsius}", color = Color.Black)

    }
}












