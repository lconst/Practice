package com.example.practice.networking

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast")
    fun getWeatherFiveDays(
        @Query("id") id: Int = MOSCOW_ID,
        @Query("lang") lang: String = LANG,
        @Query("units") units: String = UNITS
    ): Single<WeatherResponse>

    companion object {
        const val MOSCOW_ID = 524894
        const val LANG = "ru"
        const val UNITS = "metric"
    }
}
