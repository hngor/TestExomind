package com.example.testexomind.domain.model

import com.example.testexomind.data.model.Weather


sealed class WeatherDataState {
    data class WeatherData(
        var data: MutableList<Weather> = mutableListOf()
    ): WeatherDataState()

    data class InProgress(
        var progress: Int
    ): WeatherDataState()
}