package com.infullmobile.androidhomework.presentation.weather

import android.os.Bundle
import com.infullmobile.androidhomework.domain.model.WeatherForecastFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify

class WeatherPresenterTest {

    private var model: WeatherModel = mock()
    private var view: WeatherView = mock()
    private var presenter = WeatherPresenter(model, view)
    private val testWeatherForecast = WeatherForecastFactory.createTestWeatherForecast()

    @Test
    fun shouldShowClearedDataOnBind() {
        // when
        presenter.bind(Bundle(), Bundle(), null)

        // then
        verify(view, times(1)).clearData()
    }

    @Test
    fun shouldNotShowForecastOnBind() {
        // given
        whenever(model.getWeatherForecastForCity(anyString())).thenReturn(Single.just(testWeatherForecast))

        // when
        presenter.bind(Bundle(), Bundle(), null)

        // then
        verify(view, never()).displayForecast(testWeatherForecast)
    }

    @Test
    fun shouldShowForecastWhenCallGetForecastByCityName() {
        // given
        whenever(model.getWeatherForecastForCity(anyString())).thenReturn(Single.just(testWeatherForecast))

        // when
        presenter.getWeatherByCityName(testWeatherForecast.city.name)

        // then
        verify(view, times(1)).displayForecast(testWeatherForecast)
    }

    @Test
    fun shouldShowToastWithErrorWhenCallGetForecastByCityNameProducedError() {
        // given
        whenever(model.getWeatherForecastForCity("")).thenReturn(Single.error(Throwable()))

        // when
        presenter.getWeatherByCityName("")

        // then
        verify(view, times(1)).showToastMessage(anyString())
    }

}