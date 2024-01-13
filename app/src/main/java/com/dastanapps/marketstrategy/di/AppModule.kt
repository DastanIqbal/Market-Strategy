package com.dastanapps.marketstrategy.di

import android.content.Context
import com.dastanapps.marketstrategy.db.AppDatabase
import com.dastanapps.marketstrategy.di.models.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun dispatcher() = AppDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    @Provides
    fun provideDB(@ApplicationContext ctxt: Context): AppDatabase {
        return AppDatabase.getInstance(ctxt)
    }
}