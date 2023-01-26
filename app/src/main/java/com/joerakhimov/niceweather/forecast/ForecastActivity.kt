package com.joerakhimov.niceweather.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.niceweather.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {

    private val viewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.state.observe(this){ state -> render(state) }
        container.setOnRefreshListener {
            viewModel.handleIntent(ForecastIntent.GetForecastIntent)
        }
    }

    private fun render(state: ForecastState) {
        when(state){
            is ForecastState.LoadingState -> renderLoadingState()
            is ForecastState.DataState -> renderDataState(state.forecast)
        }
    }

    private fun renderLoadingState(){
        container.isRefreshing = true
        recycler_forecast.visibility = View.GONE
    }

    private fun renderDataState(forecast: ForecastResponse) {
        container.isRefreshing = false
        recycler_forecast.visibility = View.VISIBLE
        title = forecast.location?.name
        if(forecast.daily!=null){
            recycler_forecast.layoutManager = LinearLayoutManager(this)
            recycler_forecast.adapter = ForecastAdapter(forecast.daily)
        }
    }

}