package com.joerakhimov.niceweather.solid

//data class Room(
//    val width: Double,
//    val height: Double
//)
//
//class AreaCalculator(){
//    fun calculateArea(rooms: List<Room>){
//        var total = 0.0
//        for(room in rooms){
//            total += room.height * room.width
//        }
//    }
//}

interface Space {
    fun area(): Double
}

data class RectangularSpace(
    val width: Double,
    val height: Double
): Space {
    override fun area() = height * width
}

data class CircularSpace(
    val radius: Double
): Space {
    override fun area() = 3.14 * radius * radius
}

class AreaCalculator(){
    fun calculateArea(spaceList: List<Space>){
        var total = 0.0
        for(space in spaceList){
            total += space.area()
        }
    }
}