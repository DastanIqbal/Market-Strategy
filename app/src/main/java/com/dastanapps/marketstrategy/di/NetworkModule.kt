package com.dastanapps.marketstrategy.di

import com.dastanapps.marketstrategy.network.HeaderInterceptor
import com.dastanapps.marketstrategy.network.NSEApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Arrays
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class NSEAPI

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
@MustBeDocumented
annotation class MoneyControlAPI

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .pingInterval(120, TimeUnit.MILLISECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .pingInterval(120, TimeUnit.MILLISECONDS)
            .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
        return retrofit
    }

    @NSEAPI
    @Provides
    @Singleton
    fun provideNSEApi(builder: Retrofit.Builder): NSEApi {
        val retrofit = builder.baseUrl(NSEApi.BASE_URL).build()
        return retrofit.create(NSEApi::class.java)
    }

    @MoneyControlAPI
    @Provides
    @Singleton
    fun provideMoneyControlApi(builder: Retrofit.Builder): NSEApi {
        val retrofit = builder.baseUrl(NSEApi.BASE_URL).build()
        return retrofit.create(NSEApi::class.java)
    }

}