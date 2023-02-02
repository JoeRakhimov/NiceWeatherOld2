package com.joerakhimov.niceweather.domain.usecase

import com.joerakhimov.niceweather.domain.repository.ForecastRepository
import com.joerakhimov.niceweather.domain.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn

class GetForecastUseCase(
    private val forecastRepository: ForecastRepository,
    private val locationRepository: LocationRepository
) {
    fun getForecast() = combine(
        forecastRepository.getForecast(),
        locationRepository.getLocation()
    ) { forecast, location ->
        forecast.location = location
    }.flowOn(Dispatchers.IO)
}