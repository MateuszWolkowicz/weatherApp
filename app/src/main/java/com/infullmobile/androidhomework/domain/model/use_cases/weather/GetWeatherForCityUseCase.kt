package com.infullmobile.androidhomework.domain.model.use_cases.weather

import com.infullmobile.androidhomework.domain.WeatherService
import com.infullmobile.androidhomework.domain.model.WeatherForecast
import io.reactivex.Scheduler
import io.reactivex.Single

open class GetWeatherForCityUseCase(
        private val weatherService: WeatherService,
        private val subscribeScheduler: Scheduler,
        private val observeScheduler: Scheduler
) {

    open fun getForecast(cityName: String): Single<WeatherForecast> =
            weatherService.getWeatherForecastForCity(cityName)
                    .subscribeOn(subscribeScheduler)
                    .observeOn(observeScheduler)

}
