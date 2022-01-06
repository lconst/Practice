package com.example.practice.networking

class WeatherMapper {
    fun mapFromResponse(response: WeatherListItem): Weather {
        return Weather(
            dataTxt = response.dtTxt,
            temperature = response.main.temp,
            humidity = response.main.humidity,
            icon = response.weather.first().icon,
            description = response.weather.first().description
        )
    }
}
