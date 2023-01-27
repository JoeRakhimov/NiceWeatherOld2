package com.joerakhimov.niceweather.forecast

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.niceweather.R
import com.joerakhimov.niceweather.databinding.ActivityForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_forecast.*

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {

    private val viewModel: ForecastViewModel by viewModels()
    private var binding: ActivityForecastBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forecast)
        viewModel.getForecast()
        viewModel.forecast.observe(this) { forecast ->
            showForecast(forecast)
        }
    }

    private fun showForecast(forecast: ForecastResponse) {
        title = forecast.location?.name
        if(forecast.daily!=null){
            recycler_forecast.layoutManager = LinearLayoutManager(this)
            binding?.adapter = ForecastAdapter(forecast.daily, this)
        }
    }

}