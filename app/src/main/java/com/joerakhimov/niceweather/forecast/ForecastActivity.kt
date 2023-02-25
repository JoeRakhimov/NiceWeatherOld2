package com.joerakhimov.niceweather.forecast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.niceweather.R
import com.joerakhimov.niceweather.service.DownloadJobIntentService
import com.joerakhimov.niceweather.service.DownloadService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
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

        val intent = Intent(this, DownloadService::class.java)
        val imagePath = "https://triptins.com/wp-content/uploads/2022/05/Things-To-Do-in-Lauterbrunnen-1536x1152.jpeg"
        intent.putExtra("image_path", imagePath)
//        startService(intent)
        DownloadJobIntentService.startWork(this, intent)

    }

    override fun onStop() {
        val intent = Intent(this, DownloadService::class.java)
        stopService(intent)
        super.onStop()
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
            recycler_forecast.layoutManager = LinearLayoutManager(this)
            recycler_forecast.adapter = ForecastAdapter(forecast.daily)
        }
    }

}