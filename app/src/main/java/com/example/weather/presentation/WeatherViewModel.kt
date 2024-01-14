package com.example.weather.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weather.WeatherApplication
import com.example.weather.data.Repository
import com.example.weather.domain.WeatherInfo
import com.example.weather.location.LocationTracker
import com.example.weather.resources.Resource
import kotlinx.coroutines.launch

data class WeatherState(
    val weatherInfo: WeatherInfo?=null,
    val isLoading:Boolean=false,
    val error:String? = null
)

class WeatherViewModel(
    private val repository: Repository,
    private val locationTracker: LocationTracker
):ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    init {
        loadWeatherInfo()
    }

    fun loadWeatherInfo(){
        viewModelScope.launch {
           state = state.copy(
                isLoading = true,
               error = null
            )
            locationTracker.getCurrentLocation()?.let {
                when(val result = repository.getWeatherData(it.latitude,it.longitude)){
                    is Resource.Success->{
                        state=state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error->{
                        state=state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
    companion object{
         val Factory : ViewModelProvider.Factory = viewModelFactory{
            initializer {
             val application = (this[APPLICATION_KEY] as WeatherApplication)
             WeatherViewModel(
                 repository = application.container.repository,
                 locationTracker = application.defaultLocationTracker
             )
            }
        }
    }
}