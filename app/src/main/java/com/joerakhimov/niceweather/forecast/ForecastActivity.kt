package com.joerakhimov.niceweather.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.niceweather.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity(), ForecastView {

    @Inject
    lateinit var presenter: ForecastPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.handleIntent(ForecastIntent.GetForecastIntent)
    }

    override fun render(state: ForecastState) {
        when(state){
            is ForecastState.LoadingState -> showLoading()
            is ForecastState.DataState -> showForecast(state.forecast)
        }
    }

    private fun showLoading(){
        progress.visibility = View.VISIBLE
    }

    private fun showForecast(forecast: ForecastResponse) {
        progress.visibility = View.GONE
        title = forecast.location?.name
        if(forecast.daily!=null){
            recycler_forecast.layoutManager = LinearLayoutManager(this)
            recycler_forecast.adapter = ForecastAdapter(forecast.daily)
        }
    }

}