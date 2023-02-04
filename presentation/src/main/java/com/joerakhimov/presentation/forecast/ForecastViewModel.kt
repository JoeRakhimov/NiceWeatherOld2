package com.joerakhimov.presentation.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joerakhimov.niceweather.domain.usecase.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val useCase: GetForecastUseCase,
    private val converter: DailyForecastConverter
) : ViewModel() {

    private val _dailyForecast =
        MutableStateFlow<UiState<List<DailyItem>>>(UiState.Loading)
    val dailyForecast: StateFlow<UiState<List<DailyItem>>> = _dailyForecast

    fun loadForecast() {
        viewModelScope.launch {
            useCase.execute(GetForecastUseCase.Request())
                .map {
                    converter.convert(it)
                }
                .collect {
                    _dailyForecast.value = it
                }
        }
    }

}