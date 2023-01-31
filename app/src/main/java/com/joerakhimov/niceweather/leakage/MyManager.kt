package com.joerakhimov.niceweather.leakage

object MyManager {

    private val listeners = mutableListOf<MyListener>()

    fun addListener(listener: MyListener){
        listeners.add(listener)
    }

    fun removeListener(listener: MyListener){
        listeners.remove(listener)
    }

}