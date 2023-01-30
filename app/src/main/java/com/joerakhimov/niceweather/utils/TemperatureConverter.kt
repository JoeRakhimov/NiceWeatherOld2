package com.joerakhimov.niceweather.utils

import kotlinx.coroutines.flow.Flow

interface TemperatureConverter {

    fun fromCelsiusToFahrenheit(celsius: Double, callback: (Double) -> Unit)
    suspend fun fromCelsiusToFahrenheitUsingCoroutines(celsius: Double): Double
    fun fromCelsiusToFahrenheitUsingFlows(celsius: Double): Flow<Double>

}