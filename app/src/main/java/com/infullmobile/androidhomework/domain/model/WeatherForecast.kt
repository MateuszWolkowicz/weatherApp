package com.infullmobile.androidhomework.domain.model

data class WeatherForecast(
        val city: City,
        val weatherType: WeatherType,
        val temperature: String,
        val pressure: String
)
