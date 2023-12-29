package com.dastanapps.marketstrategy.data

import com.dastanapps.marketstrategy.di.NSEAPI
import com.dastanapps.marketstrategy.network.NSEApi
import com.dastanapps.marketstrategy.network.NSEApi.Companion.GET_MARKET_STATUS
import com.dastanapps.marketstrategy.network.NetworkExecutor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

interface INSEServices {
    suspend fun getIndices(): String
}

class NSEDataSource @Inject constructor(
    @NSEAPI private val nseApi: NSEApi
) : INSEServices {
    override suspend fun getIndices(): String = withContext(Dispatchers.IO) {
        return@withContext NetworkExecutor.get(GET_MARKET_STATUS)
    }
}