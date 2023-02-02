package com.joerakhimov.niceweather.domain.repository

import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {
    fun getForecast(): Flow<ForecastResponseEntity>
}