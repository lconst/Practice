package com.example.practice.networking

data class Weather(
    val dataTxt: String,
    val temperature: Double,
    val humidity: Int,
    val icon: String,
    val description: String
)
