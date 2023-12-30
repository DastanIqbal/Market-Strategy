package com.dastanapps.marketstrategy.data

import com.dastanapps.marketstrategy.di.models.AppDispatchers
import com.dastanapps.marketstrategy.network.NSEApi.Companion.GET_HISTORICAL_EQUITY
import com.dastanapps.marketstrategy.network.NetworkExecutor
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

interface INSEServices {
    suspend fun historicalData(symbol: String, from: String, to: String): String
}

class NSEDataSource @Inject constructor(
    private val networkExecutor: NetworkExecutor,
    private val dispatcher: AppDispatchers
) : INSEServices {

    override suspend fun historicalData(symbol: String, from: String, to: String): String =
        withContext(dispatcher.io) {
            return@withContext networkExecutor.get(GET_HISTORICAL_EQUITY.format(symbol, from, to))
        }


}