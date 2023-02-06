package com.joerakhimov.presentation.forecast

import com.clean.presentation_common.state.UiSingleEvent

sealed class ForecastUiSingleEvent : UiSingleEvent {
    data class OpenLocationScreen(val navRoute: String) : ForecastUiSingleEvent()
}