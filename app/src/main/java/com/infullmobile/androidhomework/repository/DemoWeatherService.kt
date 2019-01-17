package com.infullmobile.androidhomework.repository

import com.infullmobile.androidhomework.domain.WeatherService
import com.infullmobile.androidhomework.domain.model.WeatherForecast
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class DemoWeatherService : WeatherService {
    override fun getWeatherForecastForCity(cityName: String): Single<WeatherForecast> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private const val DELAY_TIME = 1000L
        private val DELAY_TIME_UNIT = TimeUnit.MILLISECONDS
    }
}
