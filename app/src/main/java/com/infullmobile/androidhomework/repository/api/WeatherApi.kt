package com.infullmobile.androidhomework.repository.api

import com.infullmobile.androidhomework.repository.api.responses.models.WeatherForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeatherByCityName(@Query("q") city: String, @Query("appid") appId: String): Single<WeatherForecastResponse>
}
