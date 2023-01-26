package com.joerakhimov.niceweather.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(api: ForecastApi) : ViewModel() {

    private fun getForecast() {
        viewModelScope.launch {
//            view.render(ForecastState.LoadingState)
//            val forecast = api.getForecast()
//            forecast.daily?.let { view.render(ForecastState.DataState(forecast)) }
        }
    }

}