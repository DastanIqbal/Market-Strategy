package com.dastanapps.marketstrategy.utils

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

/**
 *
 * Created by Iqbal Ahmed on 13/01/2024
 *
 */


fun InputStream.string(): String {
    // Create an InputStreamReader.
    val inputStreamReader = InputStreamReader(this)

    // Create a BufferedReader.
    val bufferedReader = BufferedReader(inputStreamReader)

    // Read the characters from the BufferedReader.
    val stringBuilder = StringBuilder()
    while (true) {
        val line = bufferedReader.readLine() ?: break
        stringBuilder.append(line)
    }

    // Close the BufferedReader.
    bufferedReader.close()

    // Convert the StringBuilder to a String.
    val string = stringBuilder.toString()
    return string
}