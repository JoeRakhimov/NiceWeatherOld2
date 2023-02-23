package com.joerakhimov.niceweather.forecast

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.joerakhimov.niceweather.R
import com.joerakhimov.niceweather.worker.DownloadWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {

    @Inject
    lateinit var api: ForecastApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        downloadImage()
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

    private fun downloadImage(){
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(constraints)
            .build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueue(downloadRequest)

        workManager.getWorkInfoByIdLiveData(downloadRequest.id).observe(this, Observer { info->
            if(info.state.isFinished){
                val imageFile = File(externalMediaDirs.first(), "lauterbrunnen.jpg")
                displayImage(imageFile.absolutePath)
            }
        })
    }

    private fun displayImage(imagePath: String){
        GlobalScope.launch(Dispatchers.Main){
            val bitmap = loadImageFromFile(imagePath)
            image.setImageBitmap(bitmap)
        }
    }

    private suspend fun loadImageFromFile(imagePath: String) = withContext(Dispatchers.IO){
        BitmapFactory.decodeFile(imagePath)
    }

}