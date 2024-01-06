package com.dastanapps.marketstrategy.data.nse.models.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

/**
 * Author: Iqbal Ahmed
 * Date: 07/01/2024
 * Description: Brief description of the file or purpose.
 */

@Keep
data class FutureOptionIndicesResponse(
    @SerializedName("filtered")
    val filtered: Filtered?,
    @SerializedName("records")
    val records: Records?
) {
    @Keep
    data class Filtered(
        @SerializedName("CE")
        val cE: TotalCallPut?,
        @SerializedName("data")
        val `data`: List<Data?>?,
        @SerializedName("PE")
        val pE: TotalCallPut?
    ) {
        @Keep
        data class TotalCallPut(
            @SerializedName("totOI")
            val totOI: Int?,
            @SerializedName("totVol")
            val totVol: Int?
        )

        @Keep
        data class Data(
            @SerializedName("CE")
            val cE: CallPutData?,
            @SerializedName("expiryDate")
            val expiryDate: String?,
            @SerializedName("PE")
            val pE: CallPutData?,
            @SerializedName("strikePrice")
            val strikePrice: Long?
        ) {
            @Keep
            data class CallPutData(
                @SerializedName("askPrice")
                val askPrice: Double?,
                @SerializedName("askQty")
                val askQty: Int?,
                @SerializedName("bidQty")
                val bidQty: Int?,
                @SerializedName("bidprice")
                val bidprice: Double?,
                @SerializedName("change")
                val change: Double?,
                @SerializedName("changeinOpenInterest")
                val changeinOpenInterest: Double?,
                @SerializedName("expiryDate")
                val expiryDate: String?,
                @SerializedName("identifier")
                val identifier: String?,
                @SerializedName("impliedVolatility")
                val impliedVolatility: Double?,
                @SerializedName("lastPrice")
                val lastPrice: Double?,
                @SerializedName("openInterest")
                val openInterest: Double?,
                @SerializedName("pChange")
                val pChange: Double?,
                @SerializedName("pchangeinOpenInterest")
                val pchangeinOpenInterest: Double?,
                @SerializedName("strikePrice")
                val strikePrice: Long?,
                @SerializedName("totalBuyQuantity")
                val totalBuyQuantity: Int?,
                @SerializedName("totalSellQuantity")
                val totalSellQuantity: Int?,
                @SerializedName("totalTradedVolume")
                val totalTradedVolume: Int?,
                @SerializedName("underlying")
                val underlying: String?,
                @SerializedName("underlyingValue")
                val underlyingValue: Double?
            )
        }
    }

    @Keep
    data class Records(
        @SerializedName("data")
        val `data`: List<Data?>?,
        @SerializedName("expiryDates")
        val expiryDates: List<String?>?,
        @SerializedName("strikePrices")
        val strikePrices: List<Long?>?,
        @SerializedName("timestamp")
        val timestamp: String?,
        @SerializedName("underlyingValue")
        val underlyingValue: Double?
    ) {
        @Keep
        data class Data(
            @SerializedName("CE")
            val cE: Filtered.Data.CallPutData?,
            @SerializedName("expiryDate")
            val expiryDate: String?,
            @SerializedName("PE")
            val pE: Filtered.Data.CallPutData?,
            @SerializedName("strikePrice")
            val strikePrice: Int?
        )
    }
}