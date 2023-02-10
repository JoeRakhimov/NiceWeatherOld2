package com.joerakhimov.niceweather.patterns

object Receiver{
    fun action(){
        // do something
    }
}

interface MyCommand {
    fun execute()
}

class ConcreteCommand(): MyCommand{
    override fun execute() {
        Receiver.action()
    }
}



