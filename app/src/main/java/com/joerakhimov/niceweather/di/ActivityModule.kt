package com.joerakhimov.niceweather.di

import android.app.Activity
import com.joerakhimov.niceweather.forecast.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    fun provideForecastView(activity: Activity): ForecastView = activity as ForecastActivity

    @Provides
    fun provideForecastPresenter(view: ForecastView, api: ForecastApi): ForecastPresenter = ForecastPresenterImpl(view, api)

}