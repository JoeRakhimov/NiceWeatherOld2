package com.joerakhimov.niceweather.data.source.local.forecast

import androidx.room.*

@Dao
interface ForecastDao {

    @Query("SELECT * FROM daily")
    suspend fun getAll(): List<DailyItemDto>

    @Query("SELECT * FROM daily WHERE date IN (:dates)")
    suspend fun loadAllByIds(dates: Array<String>): List<DailyItemDto>

    @Insert
    suspend fun insert(daily: DailyItemDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(daily: List<DailyItemDto>)

    @Update
    suspend fun update(daily: DailyItemDto)

    @Delete
    suspend fun delete(daily: DailyItemDto)

}