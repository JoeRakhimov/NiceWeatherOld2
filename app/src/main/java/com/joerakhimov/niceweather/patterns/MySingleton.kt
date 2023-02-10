package com.joerakhimov.niceweather.patterns

object MySingleton {
    private var password = "password"
    fun checkPassword(passwordToCheck: String) = passwordToCheck == password
}