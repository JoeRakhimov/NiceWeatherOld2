package com.joerakhimov.niceweather.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.niceweather.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {

    @Inject
    lateinit var api: ForecastApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getForecast()
    }

    private fun getForecast() {
        runBlocking {
            withContext(Dispatchers.IO) {
                val forecast = api.getForecast()
                forecast.daily?.let { showForecast(forecast) }
            }
        }
    }

    private fun showForecast(forecast: ForecastResponse) {
        title = forecast.location?.name
        if(forecast.daily!=null){
//            binding.recycler_forecast.layoutManager = LinearLayoutManager(this)
//            binding.recycler_forecast.adapter = ForecastAdapter(forecast.daily)
        }
    }

}