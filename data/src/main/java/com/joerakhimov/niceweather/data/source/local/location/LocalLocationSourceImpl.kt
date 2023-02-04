package com.joerakhimov.niceweather.data.source.local.location

import com.joerakhimov.niceweather.data.source.local.AppDataStore
import kotlinx.coroutines.flow.Flow

class LocalLocationSourceImpl(private val appDataStore: AppDataStore): LocalLocationSource {

    override suspend fun saveLocationName(locationName: String) {
        appDataStore.saveLocationName(locationName)
    }

    override fun getLocationName(): Flow<String> {
       return appDataStore.savedLocationName
    }

}