package com.joerakhimov.niceweather.data.source.remote

import android.location.Location
import com.google.gson.annotations.SerializedName

data class ForecastResponseModel(

	@field:SerializedName("current")
	val current: CurrentModel? = null,

	@field:SerializedName("daily")
	val daily: List<DailyItemModel>? = null,

	@field:SerializedName("location")
	val location: LocationModel? = null
)

data class CurrentModel(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("temp")
	val temp: Double? = null,

	@field:SerializedName("condition")
	val condition: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("time")
	val time: String? = null
)

data class LocationModel(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("region")
	val region: String? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)

data class HoursItemModel(

	@field:SerializedName("temp")
	val temp: Double? = null,

	@field:SerializedName("condition")
	val condition: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("time")
	val time: String? = null
)

data class DailyItemModel(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("temp_min")
	val tempMin: Double? = null,

	@field:SerializedName("condition")
	val condition: String? = null,

	@field:SerializedName("hours")
	val hours: List<HoursItemModel?>? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("temp_max")
	val tempMax: Double? = null
)
