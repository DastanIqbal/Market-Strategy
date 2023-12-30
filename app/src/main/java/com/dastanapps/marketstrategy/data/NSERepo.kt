package com.dastanapps.marketstrategy.data

import com.dastanapps.marketstrategy.data.models.HistoricalData
import com.dastanapps.marketstrategy.data.models.mapToHistoricalData
import com.dastanapps.marketstrategy.di.models.AppDispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

interface INSERepo {
    suspend fun historicalData(symbol: String, from: String, to: String): HistoricalData
}

class NSERepo @Inject constructor(
    private val nseData: NSEDataSource,
    private val dispatchers: AppDispatchers
) : INSERepo {
    override suspend fun historicalData(symbol: String, from: String, to: String): HistoricalData =
        withContext(dispatchers.io) {
            return@withContext nseData.historicalData(symbol, from, to).mapToHistoricalData()
        }

}