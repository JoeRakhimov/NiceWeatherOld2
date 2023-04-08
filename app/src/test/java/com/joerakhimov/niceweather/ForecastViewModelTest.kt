package com.joerakhimov.niceweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joerakhimov.niceweather.forecast.ForecastRepository
import com.joerakhimov.niceweather.forecast.ForecastViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

//@RunWith(MockitoJUnitRunner::class)
//class ForecastViewModelTest {

//    @get:Rule
//    val taskExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    lateinit var forecastRepository: ForecastRepository
//
//    @Mock
//    lateinit var loadingObserver: Observer<Boolean>
//
//    lateinit var viewModel: ForecastViewModel
//
//    @Before
//    fun setup() {
//        viewModel = ForecastViewModel(forecastRepository)
//        viewModel.loading.observeForever(loadingObserver)
//    }
//
//    @Test
//    fun getForecast_shouldShowLoading(){
//        viewModel.getForecast()
////        verify(loadingObserver).onChanged(eq(true))
//        Assert.assertTrue(viewModel.loading.value!!)
//    }

//}