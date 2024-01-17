package com.dastanapps.marketstrategy.utils

import com.google.gson.Gson

/**
 *
 * Created by Iqbal Ahmed on 17/01/2024
 *
 */


fun <T> String.fromJson(clazz: Class<T>): T {
    return Gson().fromJson(this, clazz)
}

fun <T> toJson(clazz: T): String {
    return Gson().toJson(clazz)
}