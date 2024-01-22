package com.dastanapps.marketstrategy.data.di

import android.content.Context
import com.dastanapps.marketstrategy.BuildConfig
import com.dastanapps.marketstrategy.data.nse.INSEDataSource
import com.dastanapps.marketstrategy.data.nse.NSEDataSource
import com.dastanapps.marketstrategy.data.nse.mock.NSEMockDatasource
import com.dastanapps.marketstrategy.di.models.AppDispatchers
import com.dastanapps.marketstrategy.network.NetworkExecutor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 *
 * Created by Iqbal Ahmed on 13/01/2024
 *
 */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideDataSource(@ApplicationContext context: Context, networkExecutor: NetworkExecutor, dispatchers: AppDispatchers): INSEDataSource{
        return /*if(BuildConfig.DEBUG) NSEMockDatasource(context) else*/ NSEDataSource(networkExecutor,dispatchers)
    }
}