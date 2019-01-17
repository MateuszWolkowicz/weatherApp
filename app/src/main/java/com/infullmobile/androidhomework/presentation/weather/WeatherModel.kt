package com.infullmobile.androidhomework.presentation.weather

import com.infullmobile.androidhomework.domain.model.use_cases.weather.GetWeatherForCityUseCase

open class WeatherModel(private val getWeatherForCityUseCase: GetWeatherForCityUseCase) {

    open fun getWeatherForecastForCity(cityName: String) =
            getWeatherForCityUseCase.getForecast(cityName)
}
