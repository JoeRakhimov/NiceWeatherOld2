package com.joerakhimov.niceweather.forecast

import com.google.gson.annotations.SerializedName

data class ForecastResponse(

	@field:SerializedName("current")
	val current: Current? = null,

	@field:SerializedName("daily")
	val daily: List<DailyItem>? = null,

	@field:SerializedName("location")
	val location: Location? = null
)

data class Current(

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

data class Location(

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

data class HoursItem(

	@field:SerializedName("temp")
	val temp: Double? = null,

	@field:SerializedName("condition")
	val condition: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("time")
	val time: String? = null
)

data class DailyItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("temp_min")
	val tempMin: Double? = null,

	@field:SerializedName("condition")
	val condition: String? = null,

	@field:SerializedName("hours")
	val hours: List<HoursItem?>? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("temp_max")
	val tempMax: Double? = null
)
