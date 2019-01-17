package com.infullmobile.androidhomework.presentation.weather.di

import android.support.annotation.VisibleForTesting

import com.infullmobile.androidhomework.presentation.weather.WeatherActivity

import dagger.Component

class WeatherGraph() {

    private val builder: DaggerWeatherGraph_WeatherComponent.Builder = DaggerWeatherGraph_WeatherComponent
            .builder()
            .weatherModule(WeatherModule())

    fun inject(activity: WeatherActivity) {
        builder
                .build()
                .inject(activity)
    }

    @VisibleForTesting
    fun setWeatherModule(module: WeatherModule) {
        builder.weatherModule(module)
    }

    @WeatherScope
    @Component(modules = [WeatherModule::class, WeatherServiceModule::class])
    interface WeatherComponent {
        fun inject(activity: WeatherActivity)
    }
}
