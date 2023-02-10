package com.joerakhimov.niceweather.testing

class Question(val correctOption: String) {
    fun answer(option: String): Boolean {
        return correctOption == option
    }
}