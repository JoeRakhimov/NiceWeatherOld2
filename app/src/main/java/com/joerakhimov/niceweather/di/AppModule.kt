package com.joerakhimov.niceweather.di
import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.joerakhimov.niceweather.forecast.BASE_URL
import com.joerakhimov.niceweather.forecast.ForecastApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class HttpInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //    @Named("HttpInterceptor")
    @Provides
    @HttpInterceptor
    fun provideHttpInterceptor(@ApplicationContext context: Context): Interceptor =
        ChuckerInterceptor.Builder(context).build()

    //    @Named("LoggingInterceptor")
    @Provides
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    @Provides
    fun provideOkHttpClient(
//        @Named("HttpInterceptor") chuckerInterceptor: Interceptor,
        @HttpInterceptor httpInterceptor: Interceptor,
//        @Named("LoggingInterceptor") loggingInterceptor: Interceptor
        @LoggingInterceptor loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    fun provideApi(client: OkHttpClient): ForecastApi =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ForecastApi::class.java)

}