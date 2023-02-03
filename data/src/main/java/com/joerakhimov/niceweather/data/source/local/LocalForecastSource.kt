package com.joerakhimov.niceweather.data.source.local

import com.joerakhimov.niceweather.data.source.remote.DailyItemModel
import com.joerakhimov.niceweather.data.source.remote.ForecastResponseModel
import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import kotlinx.coroutines.flow.Flow

interface LocalForecastSource {
    fun saveDailyForecast(dailyForecast: List<DailyItemModel>)
    suspend fun getDailyForecast(): List<DailyItemModel>
}