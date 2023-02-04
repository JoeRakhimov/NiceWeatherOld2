package com.joerakhimov.niceweather.data.injection

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.joerakhimov.niceweather.data.source.local.AppDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Preferences DataStore
const val NAME_USER_PREFERENCES = "user_preferences"
private val Context.dataStore by preferencesDataStore(name = NAME_USER_PREFERENCES)

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        appContext.dataStore

    @Provides
    fun provideAppDataStore(preferencesDataStore: DataStore<Preferences>) = AppDataStore(preferencesDataStore)

}