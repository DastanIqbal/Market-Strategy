package com.dastanapps.marketstrategy.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 29/12/2023
 *
 */

class NSERepo @Inject constructor(
    val nseData: NSEDataSource
) : INSEServices {
    override suspend fun getIndices(): String = withContext(Dispatchers.IO) {
        nseData.getIndices()
    }
}