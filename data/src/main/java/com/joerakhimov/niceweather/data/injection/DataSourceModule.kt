package com.joerakhimov.niceweather.data.injection

import com.joerakhimov.niceweather.data.source.local.LocalForecastSource
import com.joerakhimov.niceweather.data.source.local.LocalForecastSourceImpl
import com.joerakhimov.niceweather.data.source.remote.RemoteForecastSource
import com.joerakhimov.niceweather.data.source.remote.RemoteForecastSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteForecastDataSource(remoteForecastDataSourceImpl: RemoteForecastSourceImpl): RemoteForecastSource

    @Binds
    abstract fun bindLocalForecastSource(localForecastSource: LocalForecastSourceImpl): LocalForecastSource

}