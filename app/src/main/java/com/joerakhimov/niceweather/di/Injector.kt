package com.joerakhimov.niceweather.di

import android.app.Activity
import android.content.Context

object Injector {

    lateinit var appComponent: AppComponent

    fun initAppComponent(context: Context){

        if(context is Activity){
            throw IllegalStateException("pass an Application as an argument to avoid memory leaks")
        }

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()

    }

}