package com.infullmobile.androidhomework.presentation.weather.di

import com.infullmobile.androidhomework.domain.WeatherService
import com.infullmobile.androidhomework.domain.model.use_cases.weather.GetWeatherForCityUseCase
import com.infullmobile.androidhomework.presentation.weather.WeatherModel
import com.infullmobile.androidhomework.presentation.weather.WeatherPresenter
import com.infullmobile.androidhomework.presentation.weather.WeatherView
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
open class WeatherModule {

    @WeatherScope
    @Provides
    internal fun providesGetWeatherForCityUseCase(weatherService: WeatherService) =
            GetWeatherForCityUseCase(weatherService, Schedulers.io(), AndroidSchedulers.mainThread())

    @WeatherScope
    @Provides
    internal fun providesWeatherView() = WeatherView()

    @WeatherScope
    @Provides
    fun providesWeatherModel(getWeatherForCityUseCase: GetWeatherForCityUseCase) =
            WeatherModel(getWeatherForCityUseCase)

    @WeatherScope
    @Provides
    open fun providesWeatherPresenter(
            model: WeatherModel,
            view: WeatherView
    ) = WeatherPresenter(model, view)


}
