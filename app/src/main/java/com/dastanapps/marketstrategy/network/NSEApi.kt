package com.dastanapps.marketstrategy.network

import org.json.JSONObject
import retrofit2.http.GET

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

enum class MergedReport(type:String){
    FAV_CAPITAL("favCapital"),
    FAV_DERIVATES("favDerivatives"),
    FAV_DEBT("favDebt");
}

interface NSEApi {

    @GET("/marketStatus")
    suspend fun getIndices(): JSONObject

    companion object {
        const val BASE_URL = "https://www.nseindia.com/api"
        val GET_MARKET_STATUS = "/marketStatus".addBaseUrl()
        val GET_MARKET_TURNOVER = "/market-turnover".addBaseUrl()
        val GET_ALL_INDICES = "/allIndices".addBaseUrl()
        val GET_INDEX_NAMES = "/index-names".addBaseUrl()
        val GET_EQUITY_MASTER = "/equity-master".addBaseUrl()
        val GET_MERGED_DAILY_REPORTS = "/merged-daily-reports?key=%s".addBaseUrl()
        val GET_SEARCH_AUTOCOMPLETE= "/search/autocomplete?q=%s".addBaseUrl()
        val GET_QUOTE_EQUITY= "/quote-equity?symbol=%s".addBaseUrl()
        //[%22EQ%22]&from=29-06-2023&to=29-12-2023"
        val GET_HISTORICAL_EQUITY= "/historical/cm/equity?symbol=%s&from=%s&to=%s".addBaseUrl()
        //NIFTY%2050&from=29-06-2023&to=29-12-2023
        val GET_HISTORICAL_INDICIES= "/historical/indicesHistory?indexType=%s&from=%s&to=%s".addBaseUrl()
        val GET_OPTION_CHAIN_INDICIES= "/option-chain-indices?symbol=%s".addBaseUrl()

        private fun String.addBaseUrl(): String{
            return BASE_URL + this
        }
    }
}