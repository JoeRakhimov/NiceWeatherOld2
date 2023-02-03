package com.joerakhimov.niceweather.data.source.remote

import retrofit2.http.GET

interface ForecastApi {

    @GET("location")
    suspend fun getLocation(): LocationModel

    @GET("forecast")
    suspend fun getForecast(): ForecastResponseModel

}