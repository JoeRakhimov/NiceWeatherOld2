package com.joerakhimov.presentation.forecast

import com.clean.presentation_common.state.UiAction

sealed class ForecastUiAction : UiAction {
    object Load : ForecastUiAction()
    data class ForecastClick(val date: String) : ForecastUiAction()
}