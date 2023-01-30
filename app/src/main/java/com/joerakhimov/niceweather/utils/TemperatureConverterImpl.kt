package com.joerakhimov.niceweather.utils

import android.annotation.SuppressLint
import android.os.AsyncTask
import javax.inject.Inject

class TemperatureConverterImpl @Inject constructor(): TemperatureConverter {

    override fun fromCelsiusToFahrenheit(celsius: Double, callback: (Double) -> Unit) {

        // Example with Handler
//        val handler = Handler(Looper.getMainLooper())
//        Thread(Runnable {
//            val fahrenheit = celsius * 1.8 + 32
//            handler.post(Runnable {
//                callback(fahrenheit)
//            })
//        }).start()

        // Example with AsyncTask
        object: AsyncTask<Double, Nothing, Double>(){
            override fun doInBackground(vararg celcius: Double?): Double {
                return celsius * 1.8 + 32
            }
            override fun onPostExecute(result: Double?) {
                super.onPostExecute(result)
                result?.let { callback(it) }
            }
        }.execute(celsius)

    }

}