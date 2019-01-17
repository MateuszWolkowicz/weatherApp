package com.infullmobile.androidhomework.repository

import com.infullmobile.androidhomework.domain.WeatherService
import com.infullmobile.androidhomework.domain.model.WeatherForecast
import com.infullmobile.androidhomework.repository.api.WeatherApi
import com.infullmobile.androidhomework.repository.mappers.WeatherForecastMapper
import io.reactivex.Single
import javax.inject.Inject

class RealWeatherService
@Inject
constructor(var weatherApi: WeatherApi)
    : WeatherService {

    private val weatherForecastMapper = WeatherForecastMapper()

    override fun getWeatherForecastForCity(cityName: String): Single<WeatherForecast> =
            weatherApi.getWeatherByCityName(cityName, WEATHER_API)
                    .map { weather -> weatherForecastMapper.weatherForecastResponseToWeatherForecast(weather) }

    companion object {
        private const val WEATHER_API = "6cbd9fd270c7f1bafc791abfd4a7c957"
    }

}