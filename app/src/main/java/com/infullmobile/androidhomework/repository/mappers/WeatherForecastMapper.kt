package com.infullmobile.androidhomework.repository.mappers

import com.infullmobile.androidhomework.domain.model.City
import com.infullmobile.androidhomework.domain.model.WeatherForecast
import com.infullmobile.androidhomework.domain.model.WeatherType
import com.infullmobile.androidhomework.repository.api.responses.models.WeatherForecastResponse

class WeatherForecastMapper {
    fun weatherForecastResponseToWeatherForecast(weatherForecastResponse: WeatherForecastResponse): WeatherForecast {
        var weatherType = WeatherType(DASH, EMPTY)

        if (weatherForecastResponse.weather!!.isNotEmpty()) {
            weatherType = WeatherType(
                    getWeatherTypeName(weatherForecastResponse),
                    createWeatherTypeURL(weatherForecastResponse))
        }

        return WeatherForecast(
                City(weatherForecastResponse.name.toString(), weatherForecastResponse.sys?.country.toString()),
                weatherType,
                weatherForecastResponse.main?.temp.toString(),
                weatherForecastResponse.main?.pressure.toString()
        )
    }

    private fun getWeatherTypeName(weatherForecastResponse: WeatherForecastResponse) =
            weatherForecastResponse.weather?.get(0)?.main.toString()

    private fun getWeatherTypeIconName(weatherForecastResponse: WeatherForecastResponse) =
            weatherForecastResponse.weather?.get(0)?.icon.toString()

    private fun createWeatherTypeURL(weatherForecastResponse: WeatherForecastResponse) =
            URL_REFIX + getWeatherTypeIconName(weatherForecastResponse) + URL_SUFFIX

    companion object {
        private const val DASH = "-"
        private const val EMPTY = ""
        private const val URL_REFIX = "http://openweathermap.org/img/w/"
        private const val URL_SUFFIX = ".png"
    }
}
