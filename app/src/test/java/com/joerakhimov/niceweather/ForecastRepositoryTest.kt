package com.joerakhimov.niceweather

import android.content.SharedPreferences
import com.joerakhimov.niceweather.forecast.ForecastApi
import com.joerakhimov.niceweather.forecast.ForecastRepository
import com.joerakhimov.niceweather.forecast.ForecastRepositoryImpl
import com.joerakhimov.niceweather.testing.MyCallback
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

//@RunWith(MockitoJUnitRunner::class)
//class ForecastRepositoryTest {

//    private lateinit var repository: ForecastRepository
//
//    @Mock
//    lateinit var api: ForecastApi
//
//    @Mock
//    lateinit var preferencesEditor: SharedPreferences.Editor
//
//    @Mock
//    lateinit var preferences: SharedPreferences
//
//    @Before
//    fun setup() {
//        whenever(preferences.edit()).thenReturn(preferencesEditor)
//        repository = ForecastRepositoryImpl(api, preferences)
//    }
//
//    @Test
//    fun getForecast_shouldGetForecastFromApi(){
//        whenever(preferences.edit()).thenReturn(preferencesEditor)
//        val forecastRepository = ForecastRepositoryImpl(api, preferences)
////        forecastRepository.getForecast()
//    }
//
//    @Test
//    fun saveLocation_shouldSaveToSharedPreferences(){
//        val location = "Tashkent"
//        repository.saveLocation(location)
//        inOrder(preferencesEditor) {
//            verify(preferencesEditor).putString(any(), eq(location))
//            verify(preferencesEditor).apply()
//        }
//    }
//
//    @Test
//    fun saveLocation_shouldNotSaveIfSameLocation() {
//        val previouslySavedLocation = "Tashkent"
//        val newLocation = "Tashkent"
//        val spyRepository = spy(repository)
//        doReturn(previouslySavedLocation)
//            .whenever(spyRepository)
//            .getLocation()
//        spyRepository.saveLocation(newLocation)
//        verify(preferencesEditor, never())
//            .putString(any(), eq(newLocation))
//    }
//
//    @Test
//    fun buildGame_shouldCallOnSuccess() {
//        val callback = mock<MyCallback>()
//        setUpRepositoryWithData(repository)
//        repository.getInfo(callback)
//        verify(callback).onSuccess(any())
//    }
//
//    @Test
//    fun buildGame_shouldCallOnError() {
//        val callback = mock<MyCallback>()
//        setUpRepositoryWithError(repository)
//        repository.getInfo(callback)
//        verify(callback).onError(any())
//    }
//
//    private fun setUpRepositoryWithData(repository: ForecastRepository) {
//        doAnswer {
//            val callback: MyCallback = it.getArgument(0)
//            callback.onSuccess("Success")
//        }.whenever(repository).getInfo(any())
//    }
//
//    private fun setUpRepositoryWithError(repository: ForecastRepository) {
//        doAnswer {
//            val callback: MyCallback = it.getArgument(0)
//            callback.onError("Error")
//        }.whenever(repository).getInfo(any())
//    }

//}