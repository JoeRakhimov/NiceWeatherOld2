package com.joerakhimov.niceweather.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.joerakhimov.niceweather.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastActivity : AppCompatActivity() {

    private lateinit var api: ForecastApi

    private fun initApi() {
        val interceptor = ChuckerInterceptor.Builder(this).build()
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ForecastApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initApi()
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
            recycler_forecast.layoutManager = LinearLayoutManager(this)
            recycler_forecast.adapter = ForecastAdapter(forecast.daily)
        }
    }

}