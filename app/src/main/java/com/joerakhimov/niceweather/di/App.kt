package com.joerakhimov.niceweather.di

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.initAppComponent(this)
    }

}