package com.dastanapps.marketstrategy.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")

        val response = chain.proceed(newRequest.build())
//        if (response.code != 200 && response.code != 403 && response.code != 401 && response.body != null) {
//            try {
//                val contentType: MediaType? = response.body!!.contentType()
//                val body: ResponseBody = ResponseBody.create(contentType, response.body!!.string())
//                val responseBuilder = response.newBuilder().body(body).code(200).build()
//                return responseBuilder
//            } catch (exp: Exception) {
//                return response
//            }
//        }

        return response
    }
}