package com.dastanapps.marketstrategy.utils


/**
 * Author: Iqbal Ahmed
 * Date: 07/01/2024
 * Description: Brief description of the file or purpose.
 */

fun Double?.toNullDouble(): Double {
    return this?:0.0
}

fun Long?.toNullDouble(): Double {
    return this?.toDouble()?:0.0
}

fun Int?.toNullDouble(): Double {
    return this?.toDouble()?:0.0
}