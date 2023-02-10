package com.joerakhimov.niceweather.solid

interface Shape {
    fun area(): Double
}

class Rectangle(
    private val width: Double,
    private val height: Double
): Shape {
    override fun area() = width * height
}

object AreaUtil {
    fun calculateArea(shapes: List<Shape>): Double {
        var total = 0.0
        for (shape in shapes) {
            total += shape.area()
        }
        return total
    }
}


