package com.dastanapps.marketstrategy.domain.models

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

data class GetDayAverageParam(val symbol: String, val days:Int)
data class GetDaysAverageB(val average: Double)