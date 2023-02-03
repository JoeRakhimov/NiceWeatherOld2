package com.joerakhimov.niceweather.data.source.local

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ForecastDto(

    @field:SerializedName("current")
    val current: CurrentDto? = null,

    @field:SerializedName("daily")
    val daily: List<DailyItemDto>? = null,

    @field:SerializedName("location")
    val location: LocationDto? = null

)

data class CurrentDto(

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

data class LocationDto(

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

@Entity(tableName = "daily")
data class HoursItemDto(

    @field:SerializedName("time")
    @PrimaryKey
    @ColumnInfo(name = "time")
    val time: String? = null,

    @field:SerializedName("temp")
    @ColumnInfo(name = "temp")
    val temp: Double? = null,

    @field:SerializedName("condition")
    @ColumnInfo(name = "condition")
    val condition: String? = null,

    @field:SerializedName("icon")
    @ColumnInfo(name = "icon")
    val icon: String? = null

)

@Entity(tableName = "daily")
data class DailyItemDto(

    @field:SerializedName("date")
    @PrimaryKey
    @ColumnInfo(name = "date")
    val date: String,

    @field:SerializedName("temp_min")
    @ColumnInfo(name = "temp_min")
    val tempMin: Double,

    @field:SerializedName("condition")
    @ColumnInfo(name = "condition")
    val condition: String,

//	@field:SerializedName("hours")
//	@Ignore
//	val hours: List<HoursItem>? = null,

    @field:SerializedName("icon")
    @ColumnInfo(name = "icon")
    val icon: String,

    @field:SerializedName("temp_max")
    @ColumnInfo(name = "temp_max")
    val tempMax: Double

)