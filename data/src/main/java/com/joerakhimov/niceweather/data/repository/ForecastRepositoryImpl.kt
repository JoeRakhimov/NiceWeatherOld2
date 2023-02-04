package com.joerakhimov.niceweather.data.repository

import com.joerakhimov.niceweather.data.source.local.forecast.LocalForecastSource
import com.joerakhimov.niceweather.data.source.local.location.LocalLocationSource
import com.joerakhimov.niceweather.data.source.remote.RemoteForecastSource
import com.joerakhimov.niceweather.domain.entity.DailyItemEntity
import com.joerakhimov.niceweather.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.*

class ForecastRepositoryImpl(
    private val remoteForecastDataSource: RemoteForecastSource,
    private val localForecastSource: LocalForecastSource,
    private val localLocationSource: LocalLocationSource
) : ForecastRepository {

    override fun getForecast(latitude: Double?, longitude: Double?): Flow<List<DailyItemEntity>> =
        localForecastSource.getForecast().flatMapLatest { dailyForecast ->
            if (dailyForecast.isEmpty()) refreshForecast(latitude, longitude)
            else flowOf(dailyForecast)
        }

    override fun refreshForecast(
        latitude: Double?,
        longitude: Double?
    ): Flow<List<DailyItemEntity>> = remoteForecastDataSource.getForecast(latitude, longitude)
        .onEach {
            it.daily?.let { daily -> localForecastSource.insertForecast(daily) }
            it.location?.name?.let { locationName -> localLocationSource.saveLocationName(locationName) }
        }.map { it.daily ?: emptyList() }

}