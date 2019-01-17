package com.infullmobile.androidhomework.domain.model

object WeatherForecastFactory {

    const val TEST_CITY_NAME = "Warsaw"
    const val TEST_CITY_COUNTRY = "Poland"
    const val TEST_TEMPERATURE = "10"
    const val TEST_PRESSURE = "100"
    val TEST_WEATHER_TYPE = WeatherType("Clouds", "www.wp.pl")
    val TEST_WEATHER_CITY = City(TEST_CITY_NAME, TEST_CITY_COUNTRY)

    fun createTestWeatherForecast() =
            WeatherForecast(
                    TEST_WEATHER_CITY,
                    TEST_WEATHER_TYPE,
                    TEST_TEMPERATURE,
                    TEST_PRESSURE
            )
}