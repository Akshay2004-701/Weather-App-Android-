package com.example.weather

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weather.presentation.WeatherCard
import com.example.weather.presentation.WeatherForecast
import com.example.weather.presentation.WeatherViewModel
import com.example.weather.ui.theme.Purple80
import com.example.weather.ui.theme.PurpleGrey40
import com.example.weather.ui.theme.WeatherTheme


class MainActivity : ComponentActivity() {

    private val viewModel:WeatherViewModel by viewModels(factoryProducer = { WeatherViewModel.Factory })
    private lateinit var permissionLauncher:ActivityResultLauncher<Array<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     permissionLauncher = registerForActivityResult(
         ActivityResultContracts.RequestMultiplePermissions()){
             viewModel.loadWeatherInfo()
         }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )


        setContent {
            WeatherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Purple80)
                    ) {
                        WeatherCard(
                            weatherState = viewModel.state ,
                            backgroundColor = PurpleGrey40
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(weatherState = viewModel.state)

                    }


                }
            }
        }
    }
}

