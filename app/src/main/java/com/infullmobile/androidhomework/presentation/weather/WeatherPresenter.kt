package com.infullmobile.androidhomework.presentation.weather

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import com.infullmobile.android.infullmvp.Presenter
import com.infullmobile.androidhomework.presentation.weather.exception.CustomExceptions

open class WeatherPresenter(
        private val model: WeatherModel,
        view: WeatherView
) : Presenter<WeatherView>(view) {


    private var customExceptions = CustomExceptions()

    override fun bind(intentBundle: Bundle, savedInstanceState: Bundle, intentData: Uri?) {
        presentedView.clearData()
    }

    @SuppressLint("CheckResult")
    fun getWeatherByCityName(cityName: String) {
        model.getWeatherForecastForCity(cityName)
                .doOnSubscribe { presentedView.loadingState() }
                .subscribe(
                        { weatherForecast ->
                            presentedView.displayForecast(weatherForecast)
                            presentedView.loadingFinishState()
                        },
                        { error ->
                            presentedView.showToastMessage(customExceptions.getException(error))
                            presentedView.loadingFinishState()
                        }
                )
    }
}
