package com.example.testexomind.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testexomind.domain.model.WeatherDataState
import com.example.testexomind.domain.usecase.GetAllWeatherDataUseCase
import kotlinx.coroutines.launch

class WeatherScreenViewModel(val getAllWeatherDataUseCase: GetAllWeatherDataUseCase = GetAllWeatherDataUseCase()) :
    ViewModel() {

    init {
        getWeatherData()
    }
    var uiState: WeatherDataState by mutableStateOf(WeatherDataState.InProgress(0))
        private set

    fun getWeatherData() {
        viewModelScope.launch {
            getAllWeatherDataUseCase().collect { state ->
                uiState = state
            }
        }
    }
}