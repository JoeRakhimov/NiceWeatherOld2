package com.joerakhimov.niceweather.forecast

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ForecastPresenterImpl(
    private val view: ForecastView,
    private val api: ForecastApi
): ForecastPresenter {

    override fun handleIntent(intent: ForecastIntent) {
        when(intent){
            is ForecastIntent.GetForecastIntent -> getForecast()
        }
    }

    private fun getForecast() {
        runBlocking {
            withContext(Dispatchers.IO) {
                view.render(ForecastState.LoadingState)
                val forecast = api.getForecast()
                forecast.daily?.let { view.render(ForecastState.DataState(forecast)) }
            }
        }
    }

}