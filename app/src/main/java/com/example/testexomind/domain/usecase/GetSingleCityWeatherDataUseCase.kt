/*
    GetSingleCityWeatherDataUseCase

    Use case class to retrieve weather data from a city.
 */

package com.example.testexomind.domain.usecase

import com.example.testexomind.data.WeatherDataSource
import com.example.testexomind.data.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSingleCityWeatherDataUseCase(val weatherDataSource: WeatherDataSource = WeatherDataSource()) {

    suspend operator fun invoke(cityName: String): Weather {
        return withContext(Dispatchers.IO) {
            weatherDataSource.getWeatherData(cityName)
        }
    }
}