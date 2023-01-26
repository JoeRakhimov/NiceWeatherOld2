package com.joerakhimov.niceweather.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ForecastIntent {
    object GetForecastIntent : ForecastIntent()
}

sealed class ForecastState {
    object LoadingState : ForecastState()
    data class DataState(val forecast: ForecastResponse) : ForecastState()
}

@HiltViewModel
class ForecastViewModel @Inject constructor(private val api: ForecastApi) : ViewModel() {

    private val _state = MutableLiveData<ForecastState>()
    val state: LiveData<ForecastState> = _state

    init {
        handleIntent(ForecastIntent.GetForecastIntent)
    }

    fun handleIntent(intent: ForecastIntent) {
        when (intent) {
            is ForecastIntent.GetForecastIntent -> getForecast()
        }
    }

    private fun getForecast() {
        _state.value = ForecastState.LoadingState
        viewModelScope.launch {
            val forecast = api.getForecast()
            _state.value = ForecastState.DataState(forecast)
        }
    }

}