package com.joerakhimov.niceweather.forecast

import android.graphics.Movie

sealed class ForecastState {
    object LoadingState : ForecastState()
    data class DataState(val forecast: ForecastResponse) : ForecastState()
}
