package com.joerakhimov.niceweather.forecast

import android.content.SharedPreferences
import android.util.Log

class ForecastRepositoryImpl(
    private val api: ForecastApi,
    private val preferences: SharedPreferences
) : ForecastRepository {
    override suspend fun getForecast(): ForecastResponse {
        return api.getForecast()
    }

    override fun saveLocation(name: String) {
        val editor = preferences.edit()
        editor.putString("Location", name)
        editor.apply()
    }
}