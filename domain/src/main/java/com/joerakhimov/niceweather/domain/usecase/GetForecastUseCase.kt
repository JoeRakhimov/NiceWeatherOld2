package com.joerakhimov.niceweather.domain.usecase

import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import com.joerakhimov.niceweather.domain.repository.ForecastRepository
import com.joerakhimov.niceweather.domain.repository.LocationRepository
import com.joerakhimov.niceweather.domain.entity.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class GetForecastUseCase(
    configuration: Configuration,
    private val forecastRepository: ForecastRepository,
    private val locationRepository: LocationRepository
) : UseCase<GetForecastUseCase.Request, GetForecastUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        if (request.latitude != null && request.longitude != null) {
            forecastRepository.getForecast(request.latitude, request.longitude).map {
                Response(it)
            }
        } else {
            combine(
                forecastRepository.getForecast(),
                locationRepository.getLocation()
            ) { forecast, location ->
                forecast.location = location
                Result.Success(forecast) as Result<ForecastResponseEntity>
                Response(forecast)
            }
        }

    data class Request(val latitude: Double?, val longitude: Double?) : UseCase.Request

    data class Response(val forecastResponseEntity: ForecastResponseEntity) : UseCase.Response

}