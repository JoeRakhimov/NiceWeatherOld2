package com.joerakhimov.niceweather.data.source.local

import com.joerakhimov.niceweather.data.source.remote.DailyItemModel
import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import kotlinx.coroutines.flow.Flow

class LocalForecastSourceImpl: LocalForecastSource {

    override fun saveDailyForecast(dailyForecast: List<DailyItemModel>) {
        TODO("Not yet implemented")
    }

    override fun getDailyForecast(): Flow<ForecastResponseEntity> {
        TODO("Not yet implemented")
    }

}