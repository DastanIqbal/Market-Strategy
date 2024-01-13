package com.dastanapps.marketstrategy.data.nse.mock

import android.content.Context
import com.dastanapps.marketstrategy.data.nse.INSEDataSource
import com.dastanapps.marketstrategy.utils.string
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 *
 * Created by Iqbal Ahmed on 13/01/2024
 *
 */

class NSEMockDatasource @Inject constructor(
    @ApplicationContext private val context: Context
): INSEDataSource {
    override suspend fun historicalData(symbol: String, from: String, to: String): String {
        return ""
    }

    override suspend fun fnoData(symbol: String): String {
        return context.assets.open("fno_mock.json").string()
    }
}