package com.joerakhimov.niceweather.data.repository

import com.joerakhimov.niceweather.data.source.local.LocalForecastDataSource
import com.joerakhimov.niceweather.data.source.remote.RemoteForecastDataSource
import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import com.joerakhimov.niceweather.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class ForecastRepositoryImpl(
    private val remoteForecastDataSource: RemoteForecastDataSource,
    private val localForecastDataSource: LocalForecastDataSource
) : ForecastRepository {

    override fun getForecast(latitude: Double?, longitude: Double?): Flow<ForecastResponseEntity> {
        return remoteForecastDataSource.getForecast(latitude, longitude)
            .onEach {
                it.daily?.let { it1 -> localForecastDataSource.insertForecast(it1) }
            }
    }

}