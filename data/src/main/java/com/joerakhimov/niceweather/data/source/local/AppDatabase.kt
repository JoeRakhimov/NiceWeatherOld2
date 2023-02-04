package com.joerakhimov.niceweather.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joerakhimov.niceweather.data.source.local.forecast.DailyItemDto
import com.joerakhimov.niceweather.data.source.local.forecast.ForecastDao

@Database(entities = [DailyItemDto::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun forecastDao(): ForecastDao
}