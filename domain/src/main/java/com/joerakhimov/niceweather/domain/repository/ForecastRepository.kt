package com.joerakhimov.niceweather.domain.repository

import com.joerakhimov.niceweather.domain.entity.DailyItemEntity
import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    fun getForecast(latitude: Double? = null, longitude: Double? = null): Flow<List<DailyItemEntity>>
    fun refreshForecast(latitude: Double? = null, longitude: Double? = null): Flow<List<DailyItemEntity>>
}