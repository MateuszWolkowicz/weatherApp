package com.infullmobile.androidhomework.presentation.weather

import com.infullmobile.android.infullmvp.InFullMvpActivity
import com.infullmobile.androidhomework.presentation.weather.di.WeatherGraph
import javax.inject.Inject

class WeatherActivity : InFullMvpActivity<WeatherPresenter, WeatherView>() {

    @Inject
    lateinit var weatherPresenter: WeatherPresenter
    @Inject
    lateinit var weatherView: WeatherView
    val weatherGraph = WeatherGraph()

    override val presenter: WeatherPresenter get() = weatherPresenter
    override val presentedView: WeatherView get() = weatherView

    override fun injectIntoGraph() = weatherGraph.inject(this)

}
