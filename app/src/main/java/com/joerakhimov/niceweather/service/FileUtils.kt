package com.joerakhimov.niceweather.service

import com.joerakhimov.niceweather.forecast.BASE_URL
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

object FileUtils {

  fun downloadImage(file: File, imageDownloadPath: String) {
    val imageUrl = URL("$BASE_URL/files/$imageDownloadPath")

    val connection = imageUrl.openConnection() as HttpURLConnection
    connection.doInput = true
    connection.connect()

    val inputStream = connection.inputStream

    val outputStream = FileOutputStream(file)
    outputStream.use { output ->
      val buffer = ByteArray(4 * 1024)
      var byteCount = inputStream.read(buffer)

      while (byteCount > 0) {
        output.write(buffer, 0, byteCount)
        byteCount = inputStream.read(buffer)
      }

      output.flush()
    }
  }
}