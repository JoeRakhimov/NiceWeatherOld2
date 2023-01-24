package com.joerakhimov.niceweather.forecast

interface ForecastPresenter {
    fun handleIntent(intent: ForecastIntent)
}