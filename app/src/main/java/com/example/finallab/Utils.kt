package com.example.finallab

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.finallab.RetrofitApi.RetrofitClient
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Utils {
    fun getImageBitMap(url: String): Bitmap? {
        var baseUrl: String = RetrofitClient.getBaseUrl()
        var fullUrl = baseUrl + url
        var imgBitmap: Bitmap? = null

        Thread(Runnable {
            val url = URL(fullUrl)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val `is`: InputStream = connection.getInputStream()
            imgBitmap = BitmapFactory.decodeStream(`is`)
        }).start() // The only problem is that this thread not always make it's work in time, so list items without images could happen sometimes
        Thread.sleep(500)
        return imgBitmap?.let { resizeBitmap(it, 150, 150) }
    }

    fun getImageBitMap(url: String, size: Int): Bitmap? {
        var baseUrl: String = RetrofitClient.getBaseUrl()
        var fullUrl = baseUrl + url
        var imgBitmap: Bitmap? = null

        Thread(Runnable {
            val url = URL(fullUrl)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val `is`: InputStream = connection.getInputStream()
            imgBitmap = BitmapFactory.decodeStream(`is`)
        }).start() // The only problem is that this thread not always make it's work in time, so list items without images could happen sometimes
        Thread.sleep(500)
        return imgBitmap?.let { resizeBitmap(it, size, size) }
    }

    // Method to resize a bitmap programmatically
    private fun resizeBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        return Bitmap.createScaledBitmap(
            bitmap,
            width,
            height,
            false
        )
    }

}