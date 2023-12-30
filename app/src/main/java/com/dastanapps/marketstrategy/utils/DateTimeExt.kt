package com.dastanapps.marketstrategy.utils

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

const val DAY_MON_YEAR = "dd-MM-yyyy"

fun currentDateTime(to: String = DAY_MON_YEAR, locale: Locale = Locale.ENGLISH): String {
    val formatter = DateTimeFormatter.ofPattern(to, locale)
    return try {
        LocalDateTime.now(ZoneId.systemDefault()).format(formatter)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun String.addDays(days: Long, to: String = DAY_MON_YEAR, locale: Locale = Locale.ENGLISH): String {
    val formatter = DateTimeFormatter.ofPattern(to, locale)
    return try {
        OffsetDateTime.parse(this)
            .plusDays(days)
            .format(formatter)
    } catch (e: Exception) {
        e.printStackTrace()
       this
    }
}

fun String.minusDays(days: Long, to: String = DAY_MON_YEAR, locale: Locale = Locale.ENGLISH): String {
    val formatter = DateTimeFormatter.ofPattern(to, locale)
    return try {
        LocalDate.parse(this, formatter).minusDays(days).format(formatter)
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}