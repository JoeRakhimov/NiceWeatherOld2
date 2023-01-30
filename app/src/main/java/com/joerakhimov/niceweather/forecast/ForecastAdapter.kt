package com.joerakhimov.niceweather.forecast

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joerakhimov.niceweather.R
import com.joerakhimov.niceweather.utils.TemperatureConverter
import kotlinx.android.synthetic.main.listitem_forecast.view.*

class ForecastAdapter(
    private val dailyForecast: List<DailyItem>,
    private val temperatureConverter: TemperatureConverter
) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.listitem_forecast, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dayForecast = dailyForecast[position]
        viewHolder.itemView.text_date.text = dayForecast.date
        if (dayForecast.tempMin != null && dayForecast.tempMax != null) {
            val shouldTemperatureBeShownInCelsius = true
            if (shouldTemperatureBeShownInCelsius) {
                viewHolder.itemView.text_temp.text =
                    "${dayForecast.tempMin}°/${dayForecast.tempMax}°C"
            } else {
                val tempMin: Double? = null
                val tempMax: Double? = null
                temperatureConverter.fromCelsiusToFahrenheit(dayForecast.tempMin) {
                    if (tempMax != null) showTemperature(viewHolder, it, tempMax)
                }
                temperatureConverter.fromCelsiusToFahrenheit(dayForecast.tempMax) {
                    if (tempMin != null) showTemperature(viewHolder, tempMin, it)
                }
            }

        }

        viewHolder.itemView.text_condition.text = dayForecast.condition
    }

    private fun showTemperature(
        viewHolder: ViewHolder,
        tempMin: Double,
        tempMax: Double
    ) {
        Log.d("WeatherTag", "showTemperature")
        viewHolder.itemView.text_temp.text = "$tempMin°/$tempMax°C"
    }

    override fun getItemCount() = dailyForecast.size

}