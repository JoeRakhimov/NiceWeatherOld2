package com.joerakhimov.niceweather.forecast

import retrofit2.http.GET

const val BASE_URL = "http://api.joerakhimov.com/weather/"

interface ForecastApi {

    @GET("/weather/location")
    suspend fun getLocation(): Location

    @GET("/weather/forecast")
    suspend fun getForecast(): ForecastResponse

}