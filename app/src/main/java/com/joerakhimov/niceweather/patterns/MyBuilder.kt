package com.joerakhimov.niceweather.patterns


data class Menu(
    var mainDish: String? = null,
    var dessert: String? = null
)

abstract class MyBuilder {
    var menu = Menu()
    abstract fun addMainDish(mainDish: String): MyBuilder
    abstract fun addDessert(dessert: String): MyBuilder
    abstract fun build(): Menu
}

class MenuBuilder: MyBuilder() {

    override fun addMainDish(mainDish: String): MyBuilder  {
        menu.mainDish = mainDish
        return this
    }

    override fun addDessert(dessert: String): MyBuilder {
        menu.dessert = dessert
        return this
    }

    override fun build() = Menu()

}