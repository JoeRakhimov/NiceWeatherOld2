package com.joerakhimov.presentation.forecast

import com.joerakhimov.niceweather.domain.usecase.GetForecastUseCase
import com.joerakhimov.presentation.R

class DailyForecastConverter {

    fun convert(dailyForecastResult: Result<GetForecastUseCase.Response>): UiState<List<DailyItem>> {
        return when (dailyForecastResult) {
            is Result.Error -> {
                UiState.Error(postListResult.exception.localizedMessage.orEmpty())
            }
            is Result.Success -> {
                UiState.Success(PostListModel(
                    headerText = context.getString(
                        R.string.total_click_count,
                        postListResult.data.interaction.totalClicks
                    ),
                    items = postListResult.data.posts.map {
                        PostListItemModel(
                            it.post.id,
                            it.user.id,
                            context.getString(R.string.author, it.user.name),
                            context.getString(R.string.title, it.post.title)
                        )
                    }
                ))
            }
        }
    }

}