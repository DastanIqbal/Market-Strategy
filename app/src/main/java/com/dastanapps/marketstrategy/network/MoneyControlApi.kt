package com.dastanapps.marketstrategy.network

import org.json.JSONObject
import retrofit2.http.GET

/**
 * Author: Iqbal Ahmed
 * Date: 06/01/2024
 * Description: Brief description of the file or purpose.
 */

interface MoneyControlApi {

    @GET("/mcapi/v1/fno/options/getFnoList?stk_txt=NIFTY")
    suspend fun fnoList(): JSONObject

    companion object{
        const val BASE_URL = "https://api.moneycontrol.com"
        val GET_FNO_LIST = "/mcapi/v1/fno/options/getFnoList?stk_txt=%s".addBaseUrl()

        private fun String.addBaseUrl(): String{
            return NSEApi.BASE_URL + this
        }
    }
}