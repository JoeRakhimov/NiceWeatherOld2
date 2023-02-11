package com.joerakhimov.niceweather.testing

interface MyCallback {
    fun onSuccess(data: String)
    fun onError(message: String)
}