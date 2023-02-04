package com.joerakhimov.niceweather.data.source.local.forecast

import com.joerakhimov.niceweather.data.source.remote.DailyItemModel
import com.joerakhimov.niceweather.domain.entity.DailyItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalForecastSourceImpl(private val forecastDao: ForecastDao) : LocalForecastSource {

//    override fun saveDailyForecast(dailyForecast: List<DailyItemModel>) {
//        val forecast = dailyForecast.map { convert(it) }
//        forecastDao.insertAll(forecast)
//    }

    override suspend fun insertForecast(dailyForecast: List<DailyItemEntity>) {
        val forecast = dailyForecast.map { convert(it) }
        forecastDao.insertAll(forecast)
    }

//    override fun getDailyForecast(): List<DailyItemModel> {
//        val forecast = forecastDao.getAll()
//        return forecast.map { convert(it) }
//    }

    override fun getForecast(): Flow<List<DailyItemEntity>> = flow {
        forecastDao.getAll().map { convert(it) }
    }

    private fun convert(dailyItemModel: DailyItemModel) = DailyItemDto(
        date = dailyItemModel.date ?: "",
        tempMin = dailyItemModel.tempMin ?: 0.0,
        condition = dailyItemModel.condition ?: "",
        icon = dailyItemModel.icon ?: "",
        tempMax = dailyItemModel.tempMax ?: 0.0
    )

    private fun convert(dailyItemEntity: DailyItemEntity) = DailyItemDto(
        date = dailyItemEntity.date ?: "",
        tempMin = dailyItemEntity.tempMin ?: 0.0,
        condition = dailyItemEntity.condition ?: "",
        icon = dailyItemEntity.icon ?: "",
        tempMax = dailyItemEntity.tempMax ?: 0.0
    )

    private fun convert(dailyItemDto: DailyItemDto) = DailyItemModel(
        date = dailyItemDto.date,
        tempMin = dailyItemDto.tempMin,
        condition = dailyItemDto.condition,
        icon = dailyItemDto.icon,
        tempMax = dailyItemDto.tempMax
    )

}