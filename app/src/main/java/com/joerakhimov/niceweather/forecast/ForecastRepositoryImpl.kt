package com.joerakhimov.niceweather.forecast

import android.content.SharedPreferences
import android.util.Log
import com.joerakhimov.niceweather.testing.MyCallback

class ForecastRepositoryImpl(
    private val api: ForecastApi,
    private val preferences: SharedPreferences
) : ForecastRepository {

    override suspend fun getForecast(): ForecastResponse {
        return api.getForecast()
    }

    override fun saveLocation(name: String) {
        val savedLocation = getLocation()
        if(name == savedLocation) return
        val editor = preferences.edit()
        editor.putString("Location", name)
        editor.apply()
    }

    override fun getLocation(): String {
        return preferences.getString("Location", "")!!
    }

    override fun getInfo(callback: MyCallback) {
        callback.onSuccess("Success")
    }

}