package com.joerakhimov.niceweather.data.injection

import com.joerakhimov.niceweather.data.repository.ForecastRepositoryImpl
import com.joerakhimov.niceweather.domain.repository.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPostRepository(forecastRepositoryImpl: ForecastRepositoryImpl): ForecastRepository

}