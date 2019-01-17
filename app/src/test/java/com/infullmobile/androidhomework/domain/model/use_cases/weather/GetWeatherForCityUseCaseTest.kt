package com.infullmobile.androidhomework.domain.model.use_cases.weather

import com.infullmobile.androidhomework.domain.WeatherService
import com.infullmobile.androidhomework.domain.model.WeatherForecastFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GetWeatherForCityUseCaseTest {

    private var service: WeatherService = mock()
    private var useCase = GetWeatherForCityUseCase(service, Schedulers.trampoline(), Schedulers.trampoline())

    @Test
    fun shouldGetForecast() {
        // given
        whenever(service.getWeatherForecastForCity(anyString()))
                .thenReturn(Single.just(WeatherForecastFactory.createTestWeatherForecast()))

        // when
        val whatWeGot = useCase.getForecast(WeatherForecastFactory.TEST_CITY_NAME).blockingGet()

        // then
        assertThat(whatWeGot).isEqualTo(WeatherForecastFactory.createTestWeatherForecast())
    }
}