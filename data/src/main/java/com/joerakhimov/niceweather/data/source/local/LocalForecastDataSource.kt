package com.joerakhimov.niceweather.data.source.local

import com.joerakhimov.niceweather.domain.entity.DailyItemEntity
import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import kotlinx.coroutines.flow.Flow

interface LocalForecastDataSource {
    fun getForecast(): Flow<List<DailyItemEntity>>
    suspend fun insertForecast(dailyForecast: List<DailyItemEntity>)
}