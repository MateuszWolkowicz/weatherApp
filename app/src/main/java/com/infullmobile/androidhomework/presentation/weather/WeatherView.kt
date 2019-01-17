package com.infullmobile.androidhomework.presentation.weather

import android.support.annotation.LayoutRes
import android.widget.*
import com.infullmobile.android.infullmvp.PresentedActivityView
import com.infullmobile.androidhomework.R
import com.infullmobile.androidhomework.domain.model.WeatherForecast
import com.squareup.picasso.Picasso

open class WeatherView : PresentedActivityView<WeatherPresenter>() {

    @LayoutRes
    override val layoutResId = R.layout.activity_weather
    val cityName: TextView by bindView(R.id.cityName)
    val progressBar: ProgressBar by bindView(R.id.progressBar)
    val buttonGetCity: Button by bindView(R.id.getWeather_button)
    val etCityName: EditText by bindView(R.id.input_cityName)
    val weatherDataLayer: LinearLayout by bindView(R.id.weatherDataLayer)
    val temperature: TextView by bindView(R.id.temp)
    val pressure: TextView by bindView(R.id.pressure)
    val weatherType: TextView by bindView(R.id.weatherType)
    val weatherTypeImage: ImageView by bindView(R.id.weatherTypeImage)


    override fun onViewsBound() {
        buttonGetCity.setOnClickListener { buttonGetCityActionClick() }
    }

    open fun buttonGetCityActionClick() {
        presenter.getWeatherByCityName(etCityName.text.toString())
    }

    open fun showProgress() {
        progressBar.visible()
    }

    open fun hideProgress() {
        progressBar.gone()
    }

    open fun clearData() {
        cityName.text = DASH
        weatherType.text = DASH
        temperature.text = DASH
        pressure.text = DASH
        weatherTypeImage.gone()
    }

    open fun loadingState() {
        clearData()
        showProgress()
    }

    open fun loadingFinishState() {
        hideProgress()
    }

    open fun displayForecast(weatherForecast: WeatherForecast) {
        cityName.text = weatherForecast.city.name
        weatherType.text = weatherForecast.weatherType.type
        temperature.text = weatherForecast.temperature
        pressure.text = weatherForecast.pressure
        weatherTypeImage.visible()
        Picasso.with(context)
                .load(weatherForecast.weatherType.imageUrl)
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(weatherTypeImage)
    }

    open fun showToastMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val DASH = "-"
    }
}

