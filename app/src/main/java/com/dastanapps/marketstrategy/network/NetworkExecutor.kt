package com.dastanapps.marketstrategy.network

import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import java.net.HttpURLConnection
import java.net.URL
import java.util.Collections
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

class NetworkExecutor @Inject constructor() {

    fun get(url: String): String {
        val url = URL(url)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.addRequestProperty(
            "Accept",
            "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8"
        )
        connection.connect()

        val response = connection.inputStream.bufferedReader().use { it.readText() }
        connection.disconnect()

        return response
    }

    fun okhttpGet(url: String): String {
        val client = OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
        val request = Request.Builder()
            .url(url)
            .addHeader(
                "Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8"
            )
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string()!!
    }
}