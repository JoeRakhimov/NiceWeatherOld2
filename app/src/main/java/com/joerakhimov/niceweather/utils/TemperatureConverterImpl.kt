package com.joerakhimov.niceweather.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import dagger.hilt.android.qualifiers.ActivityContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TemperatureConverterImpl @Inject constructor() : TemperatureConverter {

    // Handler
//    override fun fromCelsiusToFahrenheit(celsius: Double, callback: (Double) -> Unit) {
//        val handler = Handler(Looper.getMainLooper())
//        Thread(Runnable {
//            val fahrenheit = celsius * 1.8 + 32
//            handler.post(Runnable {
//                callback(fahrenheit)
//            })
//        }).start()
//    }

    // AsyncTask
//    override fun fromCelsiusToFahrenheit(celsius: Double, callback: (Double) -> Unit) {
//        object: AsyncTask<Double, Nothing, Double>(){
//            override fun doInBackground(vararg celcius: Double?): Double {
//                return celsius * 1.8 + 32
//            }
//            override fun onPostExecute(result: Double?) {
//                super.onPostExecute(result)
//                result?.let { callback(it) }
//            }
//        }.execute(celsius)

    // RxJava
    override fun fromCelsiusToFahrenheit(celsius: Double, callback: (Double) -> Unit) {
        Single.create {
            val fahrenheit = celsius * 1.8 + 32
            it.onSuccess(fahrenheit)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback(it)
            }, { Log.d("WeatherTag", "Something went wrong") })
    }

    // Coroutines
    override suspend fun fromCelsiusToFahrenheitUsingCoroutines(celsius: Double): Double {
        return withContext(Dispatchers.IO){
            celsius * 1.8 + 32
        }
    }

    // Coroutines
    override suspend fun fromCelsiusToFahrenheitUsingFlows(celsius: Double): Flow<Double> {
        return flow {
            this.emit(celsius * 1.8 + 32)
        }
    }

}