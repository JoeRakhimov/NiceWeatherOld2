package com.joerakhimov.niceweather.domain.usecase

import com.joerakhimov.niceweather.domain.entity.DailyItemEntity
import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import com.joerakhimov.niceweather.domain.repository.ForecastRepository
import com.joerakhimov.niceweather.domain.repository.LocationRepository
import com.joerakhimov.niceweather.domain.entity.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
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
            locationRepository.getLocation()
                .flatMapLatest { location ->
                    forecastRepository.getForecast(request.latitude, request.longitude).map { dailyForecast ->
                        Response(dailyForecast)
                    }
                }
        }

    data class Request(val latitude: Double?, val longitude: Double?) : UseCase.Request

    data class Response(val forecastResponseEntity: List<DailyItemEntity>) : UseCase.Response

}