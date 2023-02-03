package com.joerakhimov.niceweather.data.source.remote

import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import kotlinx.coroutines.flow.Flow

interface RemoteForecastDataSource {
    fun getForecast(latitude: Double?, longitude: Double?): Flow<ForecastResponseEntity>
}