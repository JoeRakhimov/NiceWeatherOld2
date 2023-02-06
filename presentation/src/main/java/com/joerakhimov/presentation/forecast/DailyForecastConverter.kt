package com.joerakhimov.presentation.forecast

import com.joerakhimov.niceweather.domain.usecase.GetForecastUseCase
import com.joerakhimov.niceweather.domain.entity.Result
import com.joerakhimov.presentation.common.UiState

class DailyForecastConverter {

    fun convert(dailyForecastResult: Result<GetForecastUseCase.Response>): UiState<List<DailyItem>> {
        return when (dailyForecastResult) {
            is Result.Error -> {
                UiState.Error(dailyForecastResult.exception.localizedMessage.orEmpty())
            }
            is Result.Success -> {
                UiState.Success(dailyForecastResult.data.forecastResponseEntity.map {
                    DailyItem(
                        date = it.date,
                        tempMin = it.tempMin,
                        condition = it.condition,
                        icon = it.icon,
                        tempMax = it.tempMax
                    )
                })
            }
        }
    }

}