package com.infullmobile.androidhomework.presentation.weather

import android.view.View
import com.infullmobile.android.infullmvp.basetest.InFullMvpActivityBaseTest
import com.infullmobile.androidhomework.domain.model.WeatherForecastFactory
import com.infullmobile.androidhomework.presentation.weather.di.WeatherModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WeatherViewTest : InFullMvpActivityBaseTest<WeatherActivity, WeatherPresenter, WeatherView>() {

    private val testWeatherForecast = WeatherForecastFactory.createTestWeatherForecast()

    @Test
    fun shouldDisplayForecast() {
        // when
        testedView.displayForecast(testWeatherForecast)

        // then
        assertThat(testedView.cityName.text).isEqualTo(testWeatherForecast.city.name)
    }

    @Test
    fun shouldDisplayDashesInDataWhenLoading() {
        // when
        testedView.loadingState()

        // then
        assertThat(testedView.cityName.text).isEqualTo("-")
    }

    @Test
    fun shouldDisplayProgressBarWhenLoading() {
        // when
        testedView.loadingState()

        // then
        assertThat(testedView.progressBar.visibility).isEqualTo(View.VISIBLE)
    }

    @Test
    fun shouldNotDisplayProgressBarWhenLoadingFinish() {
        // when
        testedView.loadingFinishState()

        // then
        assertThat(testedView.progressBar.visibility).isEqualTo(View.GONE)
    }

    override fun substituteModules(activity: WeatherActivity) {
        activity.weatherGraph.setWeatherModule(TestWeatherModule())
    }

    override val testActivityClass: Class<WeatherActivity>
        get() = WeatherActivity::class.java

    private inner class TestWeatherModule : WeatherModule() {
        override fun providesWeatherPresenter(model: WeatherModel, view: WeatherView): WeatherPresenter = mock(WeatherPresenter::class.java)
    }
}