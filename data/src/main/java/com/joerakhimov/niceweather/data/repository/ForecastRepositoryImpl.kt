package com.joerakhimov.niceweather.data.repository

import com.joerakhimov.niceweather.data.source.local.LocalForecastDataSource
import com.joerakhimov.niceweather.data.source.remote.RemoteForecastSource
import com.joerakhimov.niceweather.domain.entity.DailyItemEntity
import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import com.joerakhimov.niceweather.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.*

class ForecastRepositoryImpl(
    private val remoteForecastDataSource: RemoteForecastSource,
    private val localForecastDataSource: LocalForecastDataSource
) : ForecastRepository {

    override fun getForecast(latitude: Double?, longitude: Double?): Flow<List<DailyItemEntity>> {
        return localForecastDataSource.getForecast()

//        return remoteForecastDataSource.getForecast(latitude, longitude)
//            .onEach { forecast ->
//                forecast.daily?.let { daily -> localForecastDataSource.insertForecast(daily) }
//            }.map { it.daily ?: emptyList() }
    }

    override fun refreshForecast(
        latitude: Double?,
        longitude: Double?
    ): Flow<List<DailyItemEntity>> {
        TODO("Not yet implemented")
    }

}