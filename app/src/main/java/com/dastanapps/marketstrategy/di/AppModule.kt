package com.dastanapps.marketstrategy.di

import com.dastanapps.marketstrategy.di.models.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers


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
}