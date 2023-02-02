package com.joerakhimov.niceweather.domain.repository

import com.joerakhimov.niceweather.domain.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocation(): Flow<LocationEntity>
}