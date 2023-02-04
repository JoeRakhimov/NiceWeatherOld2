package com.joerakhimov.presentation.forecast

data class DailyItem(
    val date: String? = null,
    val tempMin: Double? = null,
    val condition: String? = null,
    val icon: String? = null,
    val tempMax: Double? = null
)