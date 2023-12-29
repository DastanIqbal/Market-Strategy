package com.dastanapps.marketstrategy.network

import java.net.HttpURLConnection
import java.net.URL

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

object NetworkExecutor {

    fun get(url: String): String {
        val url = URL(url)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()

        val response = connection.inputStream.bufferedReader().use { it.readText() }
        connection.disconnect()

        return response
    }
}