package com.example.practice.networking.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practice.R
import com.example.practice.databinding.WeatherItemBinding
import com.example.practice.networking.Weather
import kotlin.math.roundToInt

class WeatherViewHolder(private val binding: WeatherItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(weather: Weather) {
        with(binding) {
            date.text = weather.dataTxt
            temperature.text = root.resources.getString(
                R.string.temperature,
                weather.temperature.roundToInt()
            )
            humidity.text = root.resources.getString(R.string.humidity, weather.humidity)
            description.text = weather.description
            Glide
                .with(binding.root)
                .load(root.resources.getString(R.string.url_weather_icon, weather.icon))
                .into(icon)
        }
    }
}
