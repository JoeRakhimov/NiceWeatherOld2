package com.joerakhimov.niceweather.patterns

class MyFacade {
    val networkAvailable = true
    fun getData(){
        if(networkAvailable){
            // get data from API
        } else {
            // get data from DB
        }
    }
}