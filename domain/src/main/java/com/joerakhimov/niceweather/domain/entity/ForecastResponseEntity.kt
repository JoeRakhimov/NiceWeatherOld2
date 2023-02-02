package com.joerakhimov.niceweather.domain.entity

import android.location.Location

data class ForecastResponseEntity(
	val current: CurrentEntity? = null,
	val daily: List<DailyItemEntity>? = null,
	var location: LocationEntity? = null
)

data class CurrentEntity(
	val date: String? = null,
	val temp: Double? = null,
	val condition: String? = null,
	val icon: String? = null,
	val time: String? = null
)

data class LocationEntity(
	val country: String? = null,
	val latitude: Double? = null,
	val name: String? = null,
	val id: String? = null,
	val region: String? = null,
	val longitude: Double? = null
)

data class HoursItemEntity(
	val temp: Double? = null,
	val condition: String? = null,
	val icon: String? = null,
	val time: String? = null
)

data class DailyItemEntity(
	val date: String? = null,
	val tempMin: Double? = null,
	val condition: String? = null,
	val hours: List<HoursItemEntity?>? = null,
	val icon: String? = null,
	val tempMax: Double? = null
)
