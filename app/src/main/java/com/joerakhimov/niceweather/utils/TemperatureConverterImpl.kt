package com.joerakhimov.niceweather.utils

import android.os.Handler
import android.os.Looper
import javax.inject.Inject

class TemperatureConverterImpl @Inject constructor(): TemperatureConverter {

    override fun fromCelsiusToFahrenheit(celsius: Double, callback: (Double) -> Unit) {
        val handler = Handler(Looper.getMainLooper())
        Thread(Runnable {
            val fahrenheit = celsius * 1.8 + 32
            handler.post(Runnable {
                callback(fahrenheit)
            })
        }).start()
    }

}