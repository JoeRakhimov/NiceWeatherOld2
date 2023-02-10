package com.joerakhimov.niceweather.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.niceweather.R
import com.joerakhimov.niceweather.patterns.*
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

    var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getForecast()

        // Singleton
        MySingleton.checkPassword("WrongPassword")

        // Builder
        val menu = MenuBuilder()
            .addMainDish("Plov")
            .addDessert("Pechenye")
            .build()

        // Dependency Injection
        MyInjector.inject(this)

        // Adapter
        recycler_forecast.adapter = ForecastAdapter(emptyList())

        // Facade
        MyFacade().getData()

        // Command
        val command = ConcreteCommand()
        command.execute()

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