package com.joerakhimov.niceweather.data.injection

import android.content.Context
import androidx.room.Room
import com.joerakhimov.niceweather.data.source.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "NiceWeatherDB"
        ).build()

    @Provides
    fun providesForecastDao(db: AppDatabase) = db.forecastDao()

}