package com.dastanapps.marketstrategy.di.models

import kotlinx.coroutines.CoroutineDispatcher

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

data class AppDispatchers(
    val io: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val main: CoroutineDispatcher
)