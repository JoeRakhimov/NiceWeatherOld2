package com.joerakhimov.niceweather.service

import android.app.Service
import android.content.Intent
import android.os.FileUtils
import android.os.IBinder
import android.util.Log
import com.joerakhimov.niceweather.service.FileUtils.downloadImage
import java.io.File

class DownloadService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val imagePath = intent?.getStringExtra("image_path")
        if (imagePath != null) {
            downloadImage(imagePath)
        } else {
            Log.d("Missing image path", "Stopping service")
            stopSelf()
        }

        return START_NOT_STICKY

    }

    private fun downloadImage(imagePath: String){
        Thread(Runnable {
            val file = File(applicationContext.externalMediaDirs.first(), imagePath)
            FileUtils.downloadImage(file, imagePath)
        }).start()
    }

}