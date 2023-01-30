package com.joerakhimov.niceweather.utils

interface TemperatureConverter {

    fun fromCelsiusToFahrenheit(celsius: Double, callback: (Double) -> Unit)

}