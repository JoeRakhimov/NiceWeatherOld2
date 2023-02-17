package com.joerakhimov.niceweather.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.niceweather.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine



@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {

    suspend fun <T: Any> getValue(provider: () -> T): T =
        suspendCoroutine { continuation ->
            continuation.resumeWith(Result.runCatching { provider() })
        }

    @OptIn(DelicateCoroutinesApi::class)
    fun executeBackground(action: suspend () -> Unit){
        GlobalScope.launch { action() }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun executeMain(action: suspend () -> Unit){
        GlobalScope.launch(context = Dispatchers.Main) { action() }
    }

    suspend fun square(value: Int): Int {
        delay(1000)
        return value * value
    }

    @Inject
    lateinit var api: ForecastApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getForecast()

        GlobalScope.launch(Dispatchers.Main) {
            val result = square(5)
            title = result.toString()
        }

    }

    private fun getForecast() {

//        runBlocking {
//            withContext(Dispatchers.IO) {
//                val forecast = api.getForecast()
//                forecast.daily?.let { showForecast(forecast) }
//            }
//        }

//        executeBackground {
//            val forecast = getValue { api.getForecast() }
//            executeMain { forecast.daily?.let { showForecast(forecast) } }
//        }

        executeBackground {
            val forecast = api.getForecast()
            executeMain { forecast.daily?.let { showForecast(forecast) } }
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