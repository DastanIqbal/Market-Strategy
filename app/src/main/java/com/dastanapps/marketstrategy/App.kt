package com.dastanapps.marketstrategy

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Iqbal Ahmed on 29/12/2023
 */
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        AndroidThreeTen.init(this)
    }

    companion object{
        lateinit var INSTANCE: App
    }
}
