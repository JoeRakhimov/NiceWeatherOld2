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

    override fun getForecast(latitude: Double?, longitude: Double?): Flow<List<DailyItemEntity>> =
        localForecastDataSource.getForecast().flatMapLatest { dailyForecast ->
            if (dailyForecast.isEmpty()) refreshForecast(latitude, longitude)
            else flowOf(dailyForecast)
        }

    override fun refreshForecast(
        latitude: Double?,
        longitude: Double?
    ): Flow<List<DailyItemEntity>> = remoteForecastDataSource.getForecast(latitude, longitude)
        .onEach {
            it.daily?.let { it1 -> localForecastDataSource.insertForecast(it1) }
        }.map { it.daily ?: emptyList() }

}