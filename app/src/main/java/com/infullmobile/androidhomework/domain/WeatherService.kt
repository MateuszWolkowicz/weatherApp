package com.infullmobile.androidhomework.domain

import com.infullmobile.androidhomework.domain.model.WeatherForecast
import io.reactivex.Single

interface WeatherService {

    fun getWeatherForecastForCity(cityName: String): Single<WeatherForecast>
}
