package com.joerakhimov.niceweather.forecast

import com.joerakhimov.niceweather.testing.MyCallback

interface ForecastRepository {
    suspend fun getForecast(): ForecastResponse
    fun saveLocation(name: String)
    fun getLocation(): String
    fun getInfo(callback: MyCallback)
}