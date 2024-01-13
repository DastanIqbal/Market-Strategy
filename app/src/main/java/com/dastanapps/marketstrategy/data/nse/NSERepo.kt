package com.dastanapps.marketstrategy.data.nse

import com.dastanapps.marketstrategy.data.models.FutureOptionIndicesData
import com.dastanapps.marketstrategy.data.models.HistoricalData
import com.dastanapps.marketstrategy.data.models.mapTo
import com.dastanapps.marketstrategy.data.models.mapToHistoricalData
import com.dastanapps.marketstrategy.data.nse.models.response.FutureOptionIndicesResponse
import com.dastanapps.marketstrategy.di.models.AppDispatchers
import com.dastanapps.marketstrategy.domain.models.FutureOptionParam
import com.google.gson.Gson
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

interface INSERepo {
    suspend fun historicalData(symbol: String, from: String, to: String): HistoricalData
    suspend fun fnoData(symbol: FutureOptionParam): FutureOptionIndicesData
}

class NSERepo @Inject constructor(
    private val nseData: INSEDataSource,
    private val dispatchers: AppDispatchers
) : INSERepo {
    override suspend fun historicalData(symbol: String, from: String, to: String): HistoricalData =
        withContext(dispatchers.io) {
            return@withContext nseData.historicalData(symbol, from, to).mapToHistoricalData()
        }

    override suspend fun fnoData(param: FutureOptionParam): FutureOptionIndicesData =
        withContext(dispatchers.io) {
            return@withContext Gson().fromJson(
                nseData.fnoData(param.symbol),
                FutureOptionIndicesResponse::class.java
            ).mapTo()
        }

}