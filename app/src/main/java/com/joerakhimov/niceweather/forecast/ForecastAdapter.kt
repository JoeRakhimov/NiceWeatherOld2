package com.joerakhimov.niceweather.forecast

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joerakhimov.niceweather.BR
import com.joerakhimov.niceweather.R
import com.joerakhimov.niceweather.databinding.ListitemForecastBinding

class ForecastAdapter(
    private val dailyForecast: List<DailyItem>,
    private val context: Context
): RecyclerView.Adapter<ForecastAdapter.ViewHolder>(), ForecastClickListener {

    class ViewHolder(val binding: ListitemForecastBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(dayForecast: DailyItem){
            binding.setVariable(BR.dayForecast, dayForecast)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListitemForecastBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.listitem_forecast, viewGroup, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dayForecast = dailyForecast[position]
        viewHolder.bind(dayForecast)
        viewHolder.binding.clickListener = this
    }

    override fun getItemCount() = dailyForecast.size

    override fun onClick(dayForecast: DailyItem) {
        Toast.makeText(context, dayForecast.date, Toast.LENGTH_LONG).show()
    }

}