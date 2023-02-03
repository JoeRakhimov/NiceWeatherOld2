package com.joerakhimov.niceweather.data.source.local

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.joerakhimov.niceweather.data.source.remote.DailyItemModel
import com.joerakhimov.niceweather.domain.entity.ForecastResponseEntity
import kotlinx.coroutines.flow.Flow

class LocalForecastSourceImpl(private val forecastDao: ForecastDao): LocalForecastSource {

    override fun saveDailyForecast(dailyForecast: List<DailyItemModel>) {
        val forecast = dailyForecast.map { convert(it) }
        forecastDao.insertAll(forecast)
    }

    override suspend fun getDailyForecast(): List<DailyItemModel> {
        val forecast = forecastDao.getAll()
        return forecast.map { convert(it) }
    }

    private fun convert(dailyItemModel: DailyItemModel) = DailyItemDto(
        date = dailyItemModel.date ?: "",
        tempMin = dailyItemModel.tempMin ?: 0.0,
        condition = dailyItemModel.condition ?: "",
        icon = dailyItemModel.icon ?: "",
        tempMax = dailyItemModel.tempMax ?: 0.0
    )

    private fun convert(dailyItemDto: DailyItemDto) = DailyItemModel(
        date = dailyItemDto.date,
        tempMin = dailyItemDto.tempMin,
        condition = dailyItemDto.condition,
        icon = dailyItemDto.icon,
        tempMax = dailyItemDto.tempMax
    )

}