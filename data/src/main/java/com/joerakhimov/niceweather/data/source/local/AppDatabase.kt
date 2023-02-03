package com.joerakhimov.niceweather.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DailyItemDto::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun forecastDao(): ForecastDao
}