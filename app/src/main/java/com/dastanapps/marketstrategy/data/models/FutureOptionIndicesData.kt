package com.dastanapps.marketstrategy.data.models

import com.dastanapps.marketstrategy.data.nse.models.response.FutureOptionIndicesResponse
import com.dastanapps.marketstrategy.utils.toNullDouble
import com.dastanapps.marketstrategy.utils.toNullEmpty
import com.google.gson.Gson

/**
 * Author: Iqbal Ahmed
 * Date: 07/01/2024
 * Description: Brief description of the file or purpose.
 */

enum class OptionType {
    NONE, CALL, PUT
}

data class OptionTypeData(
    val type: OptionType,
    val expiryDate: String,
    val strikePrice: Double,
    val bidPrice: Double,
    val bidQuantity: Double,
    val askPrice: Double,
    val askQuantity: Double,
    val change: Double,
    val lastTradedPrice: Double,
    val impliedVolatility: Double,
    val totalTradedVolume: Double,
    val changeinOpenInterest: Double,
    val openInterest: Double,
) {

    fun toJson(): String{
        return Gson().toJson(this)
    }
    companion object {
        fun empty() = OptionTypeData(
            OptionType.NONE,
            "", 0.0,
            0.0, 0.0,
            0.0, 0.0,
            0.0, 0.0, 0.0,
            0.0, 0.0,0.0
        )

        fun create(
            type: OptionType,
            data: FutureOptionIndicesResponse.Filtered.Data.CallPutData
        ): OptionTypeData {
            val _this = data
            return OptionTypeData(
                type = type,
                expiryDate = _this.expiryDate.toNullEmpty(),
                strikePrice = _this.strikePrice.toNullDouble(),
                bidPrice = _this.bidprice.toNullDouble(),
                bidQuantity = _this.bidQty.toNullDouble(),
                askPrice = _this.askPrice.toNullDouble(),
                askQuantity = _this.askQty.toNullDouble(),
                change = _this.change.toNullDouble(),
                lastTradedPrice = _this.lastPrice.toNullDouble(),
                impliedVolatility = _this.impliedVolatility.toNullDouble(),
                totalTradedVolume = _this.totalTradedVolume.toNullDouble(),
                changeinOpenInterest = _this.changeinOpenInterest.toNullDouble(),
                openInterest = _this.openInterest.toNullDouble(),
            )
        }
    }
}

data class FilterBy(
    val strikePrice: List<Long>,
    val expiriesDate: List<String>,
) {
    companion object {
        fun empty() = FilterBy(emptyList(), emptyList())
    }
}

data class FutureOptionIndicesData(
    val symbol: String,
    val filterBy: FilterBy,
    val expiryDate: String,
    val underlyingValue: Double,
    val call: List<OptionTypeData>,
    val put: List<OptionTypeData>,
    val records: List<OptionTypeData>
) {
    companion object {
        fun empty(): FutureOptionIndicesData {
            return FutureOptionIndicesData(
                "",
                FilterBy.empty(),
                "",
                0.0,
                emptyList(),
                emptyList(),
                emptyList()
            )
        }
    }
}

fun FutureOptionIndicesResponse.mapTo(): FutureOptionIndicesData {
    val strikePriceList = mutableListOf<Long>()
    val expiryDateList = mutableListOf<String>()
    val callList = mutableListOf<OptionTypeData>()
    val putList = mutableListOf<OptionTypeData>()
    val recordList = mutableListOf<OptionTypeData>()

    this.records?.strikePrices?.map { it?.let { it1 -> strikePriceList.add(it1) } }
    this.records?.expiryDates?.map { it?.let { it1 -> expiryDateList.add(it1) } }

    val filterBy = FilterBy(strikePriceList, expiryDateList)

    var symbol = ""
    var expiryDate = ""
    var underlyingValue = 0.0
    this.filtered?.data?.map {
        it?.expiryDate?.let { it1 -> if (expiryDate != it1) expiryDate = it1 }

        it?.cE?.run {
            this.underlying.let { it1 -> if (symbol != it1) symbol = it1.toNullEmpty() }
            callList.add(OptionTypeData.create(type = OptionType.CALL, this))
        }
        it?.pE?.run {
            this.underlying.let { it1 -> if (symbol != it1) symbol = it1.toNullEmpty() }
            putList.add(OptionTypeData.create(type = OptionType.PUT, this))
        }
    }
    this.records?.data?.map {
        it?.cE?.run { recordList.add(OptionTypeData.create(OptionType.CALL, this)) }
        it?.pE?.run { recordList.add(OptionTypeData.create(OptionType.PUT, this)) }
    }

    underlyingValue = this.records?.underlyingValue.toNullDouble()

    return FutureOptionIndicesData(
        symbol = symbol,
        filterBy = filterBy,
        expiryDate = expiryDate,
        underlyingValue = underlyingValue,
        call = callList,
        put = putList,
        records = recordList
    )
}