package com.joerakhimov.niceweather.di

import com.joerakhimov.niceweather.forecast.ForecastActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(forecastActivity: ForecastActivity)
}