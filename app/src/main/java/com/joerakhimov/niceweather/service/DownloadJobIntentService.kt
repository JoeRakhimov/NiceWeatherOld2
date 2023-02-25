package com.joerakhimov.niceweather.service

import android.app.IntentService
import android.app.Service
import android.app.Service.START_NOT_STICKY
import android.app.job.JobInfo
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.FileUtils
import android.os.IBinder
import android.util.Log
import androidx.core.app.JobIntentService
import com.joerakhimov.niceweather.service.FileUtils.downloadImage
import java.io.File

class DownloadJobIntentService : JobIntentService() {

    companion object {
        private const val JOB_ID = 10
        fun startWork(context: Context, intent: Intent){
            enqueueWork(context, DownloadService::class.java, JOB_ID, intent)
        }
    }

    override fun onHandleWork(intent: Intent) {
        val imagePath = intent?.getStringExtra("image_path")
        if (imagePath != null) {
            downloadImage(imagePath)
        } else {
            Log.d("Missing image path", "Stopping service")
            stopSelf()
        }
    }

    private fun downloadImage(imagePath: String){
        Thread(Runnable {
            val file = File(applicationContext.externalMediaDirs.first(), imagePath)
            FileUtils.downloadImage(file, imagePath)
        }).start()
    }

}