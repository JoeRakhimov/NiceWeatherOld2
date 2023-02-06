package com.joerakhimov.presentation.forecast

import androidx.lifecycle.viewModelScope
import com.joerakhimov.niceweather.domain.usecase.GetForecastUseCase
import com.joerakhimov.presentation.common.MviViewModel
import com.joerakhimov.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val useCase: GetForecastUseCase,
    private val converter: DailyForecastConverter
) : MviViewModel<DailyForecast, UiState<DailyForecast>, ForecastUiAction, ForecastUiSingleEvent>() {

    override fun initState(): UiState<DailyForecast> = UiState.Loading

    override fun handleAction(action: ForecastUiAction) {
        when (action) {
            is ForecastUiAction.Load -> {
                loadForecast()
            }
            is ForecastUiAction.ForecastClick -> {
//                submitSingleEvent(
//                    PostListUiSingleEvent.OpenPostScreen(
//                        NavRoutes.Post.routeForPost(
//                            PostInput(action.postId)
//                        )
//                    )
//                )
            }
        }

    }

    private fun loadForecast() {
        viewModelScope.launch {
            useCase.execute(GetForecastUseCase.Request())
                .map {
                    converter.convert(it)
                }
                .collect {
//                    submitState(it)
                }
        }

    }


}