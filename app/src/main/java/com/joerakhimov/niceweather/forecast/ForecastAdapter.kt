package com.joerakhimov.niceweather.forecast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joerakhimov.niceweather.R
import kotlinx.android.synthetic.main.listitem_forecast.view.*

class ForecastAdapter(
    private val dailyForecast: List<DailyItem>
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
        viewHolder.itemView.text_temp.text = "${dayForecast.tempMin}°/${dayForecast.tempMax}°C"
        viewHolder.itemView.text_condition.text = dayForecast.condition
    }

    override fun getItemCount() = dailyForecast.size

}