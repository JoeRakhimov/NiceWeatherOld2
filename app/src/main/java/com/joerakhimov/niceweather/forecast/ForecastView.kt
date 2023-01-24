package com.joerakhimov.niceweather.forecast

interface ForecastView {
    fun render(state: ForecastState)
}