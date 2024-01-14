package com.example.weather.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather.R
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    weatherState: WeatherState,
    backgroundColor:Color,
    modifier: Modifier=Modifier
){
    if (weatherState.isLoading){
        CircularProgressIndicator(
            modifier.padding(top = 350.dp, start = 200.dp)
        )
    }
    else if(weatherState.error != null){
        Row(modifier=Modifier.fillMaxSize(),Arrangement.Center) {
            Text(text = weatherState.error, color = Color.Red)
        }
    }
    else{
        weatherState.weatherInfo?.currentWeatherData?.let {
            Card(
                shape = RoundedCornerShape(10.dp),
                modifier = modifier
                    .padding(16.dp)
                    .background(backgroundColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(Color.Black)
            ) {
                Column(
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Today ${
                        it.time.format(
                            DateTimeFormatter.ofPattern("HH:mm")
                        )
                    }",
                        modifier = Modifier.align(Alignment.End),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Image(
                        painter = painterResource(id = it.weatherType.iconRes),
                        contentDescription = null,
                        modifier=Modifier.width(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${it.temperatureCelsius}°C",
                        fontSize = 50.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = it.weatherType.weatherDesc,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier=Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        WeatherDisplayItem(
                            value = it.pressure.roundToInt(),
                            unit ="hpa" ,
                            icon = ImageVector.vectorResource(R.drawable.ic_pressure)
                        )
                        WeatherDisplayItem(
                            value = it.humidity.roundToInt(),
                            unit ="%" ,
                            icon = ImageVector.vectorResource(R.drawable.ic_drop)
                        )
                        WeatherDisplayItem(
                            value = it.windSpeed.roundToInt(),
                            unit ="km/h" ,
                            icon = ImageVector.vectorResource(R.drawable.ic_wind)
                        )
                    }
                }
            }
        }
    }
}
