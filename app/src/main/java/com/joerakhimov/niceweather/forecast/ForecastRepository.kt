package com.joerakhimov.niceweather.forecast

interface ForecastRepository {
    suspend fun getForecast(): ForecastResponse
    fun saveLocation(name: String)
    fun getLocation(): String
}