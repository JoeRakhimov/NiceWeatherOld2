package com.joerakhimov.niceweather.data.source.local.location

import com.joerakhimov.niceweather.data.source.remote.DailyItemModel
import com.joerakhimov.niceweather.data.source.remote.ForecastResponseModel
import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import kotlinx.coroutines.flow.Flow

interface LocalLocationSource {
    suspend fun saveLocationName(locationName: String)
    fun getLocationName(): Flow<String>
}