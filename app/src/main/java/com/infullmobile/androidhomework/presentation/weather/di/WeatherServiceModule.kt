package com.infullmobile.androidhomework.presentation.weather.di

import com.infullmobile.androidhomework.domain.WeatherService
import com.infullmobile.androidhomework.repository.RealWeatherService
import com.infullmobile.androidhomework.repository.api.WeatherApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class WeatherServiceModule {

    @Provides
    internal fun providesWeatherService(weatherApi: WeatherApi): WeatherService = RealWeatherService(weatherApi)

    @Provides
    fun providesWeatherApiUrl(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(WEATHER_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    @Provides
    fun providesClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addNetworkInterceptor { chain ->
                    val request = chain.request()
                    val newRequest = request.newBuilder()
                            .addHeader("Accept", "application/json")
                            .build()
                    chain.proceed(newRequest)
                }
                .addNetworkInterceptor(loggingInterceptor).build()
    }

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        return loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun providesWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create<WeatherApi>(WeatherApi::class.java)
    }

    companion object {
        private const val WEATHER_API = "http://api.openweathermap.org/data/2.5/"
        private const val REQUEST_TIMEOUT = 5
    }
}
