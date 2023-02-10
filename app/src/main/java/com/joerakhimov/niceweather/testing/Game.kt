package com.joerakhimov.niceweather.testing

class Game(private val question: Question) {
    var currentScore = 0
    fun answer(option: String){
        if(question.answer(option)) currentScore++
    }
}