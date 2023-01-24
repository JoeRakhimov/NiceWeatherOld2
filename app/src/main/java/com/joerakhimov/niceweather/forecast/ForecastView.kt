package com.joerakhimov.niceweather.forecast

interface ForecastView {
    fun showForecast(forecast: ForecastResponse)
}