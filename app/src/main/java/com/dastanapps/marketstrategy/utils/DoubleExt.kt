package com.dastanapps.marketstrategy.utils

import java.text.DecimalFormat


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

fun Double.roundTo(): String {
    val df = DecimalFormat("#.##");
    val roundedNumber = df.format(this);
    return roundedNumber
}