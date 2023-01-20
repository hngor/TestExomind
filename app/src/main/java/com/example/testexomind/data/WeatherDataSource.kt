/*
    WeatherDataSource

    Class to retrieve weather data according to a city name
    To do the exercise quickly, the class retrieve hardcoded values instead
    of calling an api.
 */
package com.example.testexomind.data

import com.example.testexomind.data.model.Weather

class WeatherDataSource {
    fun getWeatherData(city: String): Weather {
        return Weather(cityName = city, temperature = 15.0, clouds = "nuageux")
    }
}