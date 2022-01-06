package com.example.practice.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class WeatherActivity : AppCompatActivity() {

    private val adapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val recycler: RecyclerView = findViewById(R.id.recycler)
        recycler.adapter = adapter

        val loader: ProgressBar = findViewById(R.id.loader)

        NetworkModule.weatherApi.getWeatherFiveDays()
            .subscribeOn(Schedulers.io())
            .map { response ->
                response.weatherList.map { responseWeather ->
                    WeatherMapper().mapFromResponse(
                        responseWeather
                    )
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { weatherList ->
                    adapter.submitList(weatherList)
                    loader.isVisible = false
                },
                { error ->
                    Log.e("ERROR", "${error.message}")
                    loader.isVisible = false
                    Toast.makeText(this, "Что-то пошло не так \n$error", Toast.LENGTH_SHORT).show()
                }
            )
    }
}
