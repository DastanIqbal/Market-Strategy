package com.dastanapps.marketstrategy.data.models

import org.json.JSONObject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

data class HistoricalData(val data:List<EquityData>)
data class EquityData(val price:String)


fun String.mapToHistoricalData(): HistoricalData {
    val json = JSONObject(this)
    val data = json.getJSONArray("data")
    val list = mutableListOf<EquityData>()
    for (i in 0 until data.length()){
        val obj = data.getJSONObject(i)
        list.add(EquityData(obj.getString("CH_CLOSING_PRICE")))
    }
    return HistoricalData(list)
}