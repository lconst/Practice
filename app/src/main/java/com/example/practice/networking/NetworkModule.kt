package com.example.practice.networking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@ExperimentalSerializationApi
object NetworkModule {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory((ScalarsConverterFactory.create()))
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)
}
