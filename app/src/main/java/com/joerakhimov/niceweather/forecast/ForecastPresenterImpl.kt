package com.joerakhimov.niceweather.forecast

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ForecastPresenterImpl(private val view: ForecastView, private val api: ForecastApi): ForecastPresenter {

    override fun getForecast() {
        runBlocking {
            withContext(Dispatchers.IO) {
                val forecast = api.getForecast()
                forecast.daily?.let { view.showForecast(forecast) }
            }
        }
    }

}