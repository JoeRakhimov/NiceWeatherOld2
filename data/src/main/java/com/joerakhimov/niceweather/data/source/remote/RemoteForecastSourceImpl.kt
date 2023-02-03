package com.joerakhimov.niceweather.data.source.remote

import com.joerakhimov.niceweather.domain.entity.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class RemoteForecastSourceImpl(private val forecastApi: ForecastApi) :
    RemoteForecastSource {

    override fun getForecast(latitude: Double?, longitude: Double?): Flow<ForecastResponseEntity> =
        flow {
            emit(forecastApi.getForecast())
        }.map { forecast ->
            convert(forecast)
        }.catch {
            throw UseCaseException.PostException(it)
        }

    private fun convert(forecastResponseModel: ForecastResponseModel) =
        ForecastResponseEntity(
            current = CurrentEntity(
                date = forecastResponseModel.current?.date,
                temp = forecastResponseModel.current?.temp,
                condition = forecastResponseModel.current?.condition,
                icon = forecastResponseModel.current?.icon,
                time = forecastResponseModel.current?.time,
            ),
            location = LocationEntity(
                country = forecastResponseModel.location?.country,
                latitude = forecastResponseModel.location?.latitude,
                name = forecastResponseModel.location?.name,
                id = forecastResponseModel.location?.id,
                region = forecastResponseModel.location?.region,
                longitude = forecastResponseModel.location?.longitude
            ),
            daily = forecastResponseModel.daily?.map {
                DailyItemEntity(
                    date = it.date,
                    tempMin = it.tempMin,
                    condition = it.condition,
                    hours = it.hours?.map { hoursItem ->
                        HoursItemEntity(
                            temp = hoursItem?.temp,
                            condition = hoursItem?.condition,
                            icon = hoursItem?.icon,
                            time = hoursItem?.time
                        )
                    },
                    icon = it.icon,
                    tempMax = it.tempMax
                )
            }
        )

}