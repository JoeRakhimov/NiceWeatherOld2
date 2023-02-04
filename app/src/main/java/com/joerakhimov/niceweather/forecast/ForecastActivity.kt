package com.joerakhimov.niceweather.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joerakhimov.niceweather.R
import com.joerakhimov.presentation.forecast.ForecastFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {

//    @Inject
//    lateinit var api: ForecastApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        getForecast()
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container, ForecastFragment.newInstance("",""))
                .commit()
        }
    }

//    private fun getForecast() {
//        runBlocking {
//            withContext(Dispatchers.IO) {
//                val forecast = api.getForecast()
//                forecast.daily?.let { showForecast(forecast) }
//            }
//        }
//    }

//    private fun showForecast(forecast: ForecastResponse) {
//        title = forecast.location?.name
//        if(forecast.daily!=null){
//            recycler_forecast.layoutManager = LinearLayoutManager(this)
//            recycler_forecast.adapter = ForecastAdapter(forecast.daily)
//        }
//    }

}