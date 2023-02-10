package com.joerakhimov.niceweather.patterns

import com.joerakhimov.niceweather.forecast.ForecastActivity

object MyInjector {
    val menu = Menu()
    fun inject(activity: ForecastActivity){
        activity.menu = menu
    }
}