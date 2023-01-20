/*
    GetAllWeatherDataUseCase

    Use case class to retrieve weather data and update progress.
 */
package com.example.testexomind.domain.usecase

import com.example.testexomind.data.model.Weather
import com.example.testexomind.domain.model.WeatherDataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

class GetAllWeatherDataUseCase(val getSingleCityWeatherDataUseCase: GetSingleCityWeatherDataUseCase = GetSingleCityWeatherDataUseCase()) {

    operator fun invoke(): Flow<WeatherDataState> = flow {
        val weatherData: MutableList<Weather> = mutableListOf()
        val cities = listOf("Rennes", "Paris", "Nantes", "Bordeaux", "Lyon")
        var currentCityIndex = 0

        //Update the progress every second
        (1..60).asFlow().collect() { value ->
            if (value == 60) {
                emit(WeatherDataState.WeatherData(weatherData))
            } else {
                //Every 10 seconds, retrieve the weather data
                if (value % 10 == 0) {
                    val data = getSingleCityWeatherDataUseCase(cities[currentCityIndex])
                    weatherData.add(data)
                    currentCityIndex++
                    if (currentCityIndex >= cities.count() - 1) {
                        currentCityIndex = 0
                    }
                }

                delay(1000)
                emit(WeatherDataState.InProgress(value))
            }
        }
    }
}