package com.example.practice.networking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("city") val city: City,
    @SerialName("cnt") val cnt: Int,
    @SerialName("cod") val cod: String,
    @SerialName("message") val message: Int,
    @SerialName("list") val weatherList: List<WeatherListItem>
)

@Serializable
data class WeatherListItem(
    @SerialName("dt") val dt: Long,
    @SerialName("pop") val pop: Double,
    @SerialName("visibility") val visibility: Int,
    @SerialName("dt_txt") val dtTxt: String,
    @SerialName("weather") val weather: List<WeatherItem>,
    @SerialName("main") val main: Main
)

@Serializable
data class Coord(
    @SerialName("lon") val lon: Double,
    @SerialName("lat") val lat: Double
)

@Serializable
data class City(
    @SerialName("country") val country: String,
    @SerialName("coord") val coord: Coord,
    @SerialName("sunrise") val sunrise: Int,
    @SerialName("timezone") val timezone: Int,
    @SerialName("sunset") val sunset: Int,
    @SerialName("name") val name: String,
    @SerialName("id") val id: Int,
    @SerialName("population") val population: Int
)

@Serializable
data class WeatherItem(
    @SerialName("icon") val icon: String,
    @SerialName("description") val description: String,
    @SerialName("main") val main: String,
    @SerialName("id") val id: Int
)

@Serializable
data class Main(
    @SerialName("temp") val temp: Double,
    @SerialName("temp_min") val tempMin: Double,
    @SerialName("grnd_level") val grndLevel: Int,
    @SerialName("temp_kf") val tempKf: Double,
    @SerialName("humidity") val humidity: Int,
    @SerialName("pressure") val pressure: Int,
    @SerialName("sea_level") val seaLevel: Int,
    @SerialName("feels_like") val feelsLike: Double,
    @SerialName("temp_max") val tempMax: Double
)
