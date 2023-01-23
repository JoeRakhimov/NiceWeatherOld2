package com.joerakhimov.niceweather.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.joerakhimov.niceweather.forecast.BASE_URL
import com.joerakhimov.niceweather.forecast.ForecastApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.dsl.module

val appModule = module {
    single(named("HttpInterceptor")) { provideHttpInterceptor(androidContext()) }
    single(named("LoggingInterceptor")) { provideLoggingInterceptor() }
    single { provideOkHttpClient(get(named("HttpInterceptor")), get(named("LoggingInterceptor"))) }
    single { provideApi(get()) }
}

fun provideHttpInterceptor(context: Context): Interceptor =
    ChuckerInterceptor.Builder(context).build()

fun provideLoggingInterceptor(): Interceptor =
    HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

fun provideOkHttpClient(
    httpInterceptor: Interceptor,
    loggingInterceptor: Interceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(httpInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

fun provideApi(client: OkHttpClient): ForecastApi =
    Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ForecastApi::class.java)

