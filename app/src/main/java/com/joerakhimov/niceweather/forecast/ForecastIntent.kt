package com.joerakhimov.niceweather.forecast

sealed class ForecastIntent{
    object GetForecastIntent: ForecastIntent()
}
