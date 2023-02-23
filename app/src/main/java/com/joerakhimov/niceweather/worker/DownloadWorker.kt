package com.joerakhimov.niceweather.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadWorker(context: Context, workerParameters: WorkerParameters): Worker(context, workerParameters) {

    override fun doWork(): Result {
        val imageUrl = URL("https://triptins.com/wp-content/uploads/2022/05/Things-To-Do-in-Lauterbrunnen-1536x1152.jpeg")
        val connection = imageUrl.openConnection() as HttpURLConnection
        connection.doInput = true
        connection.connect()

        val imagePath = "lauterbrunnen.jpg"
        val inputStraem = connection.inputStream
        val file = File(applicationContext.externalMediaDirs.first(), imagePath)

        val outputStream = FileOutputStream(file)
        outputStream.use { output ->
            val buffer = ByteArray(4 * 1024)
            var byteCount = inputStraem.read(buffer)
            while(byteCount > 0){
                output.write(buffer, 0, byteCount)
                byteCount = inputStraem.read(buffer)
            }
            output.flush()
        }

        return Result.success()
    }

}