package com.joerakhimov.niceweather.data.source.local

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val KEY_LOCATION_NAME = stringPreferencesKey("location_name")

class AppDataStore(private val dataStore: DataStore<Preferences>) {

    suspend fun saveLocationName(text: String) {
        dataStore.edit { preferences ->
            preferences[KEY_LOCATION_NAME] = text
        }
    }

    val savedLocationName: Flow<String> = dataStore.data
        .map { preferences ->
            Log.d("WeatherTag", preferences[KEY_LOCATION_NAME].orEmpty())
            preferences[KEY_LOCATION_NAME].orEmpty()
        }

}