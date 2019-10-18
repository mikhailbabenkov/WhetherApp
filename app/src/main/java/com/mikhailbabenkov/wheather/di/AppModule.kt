package com.mikhailbabenkov.wheather.di

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.mikhailbabenkov.wheather.BuildConfig
import com.mikhailbabenkov.wheather.data.api.OpenWeatherService
import com.mikhailbabenkov.wheather.data.api.StorageService
import com.mikhailbabenkov.wheather.data.datasource.WeatherLocalDataSource
import com.mikhailbabenkov.wheather.data.datasource.WeatherRemoteDataSource
import com.mikhailbabenkov.wheather.data.repository.WeatherRepository
import com.mikhailbabenkov.wheather.domain.utils.ApiConfig
import com.mikhailbabenkov.wheather.domain.utils.DateDeserializer
import com.mikhailbabenkov.wheather.domain.utils.PrivateRequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGsonConverter(dateDeserializer: JsonDeserializer<Date>): GsonConverterFactory {
        val gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, dateDeserializer)
            .create()
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideDateDeserializer(): JsonDeserializer<Date> {
        return DateDeserializer()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return PrivateRequestInterceptor()
    }

    @Singleton
    @Provides
    fun provideWeatherLocalDataSource(service: StorageService): WeatherLocalDataSource {
        return WeatherLocalDataSource(service)
    }

    @Singleton
    @Provides
    fun provideWeatherRemoteDataSource(service: OpenWeatherService): WeatherRemoteDataSource {
        return WeatherRemoteDataSource(service)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        localDataSource: WeatherLocalDataSource,
        remoteDataSource: WeatherRemoteDataSource
    ): WeatherRepository {
        return WeatherRepository(remoteDataSource, localDataSource)
    }

    @Singleton
    @Provides
    fun provideOpenWeatherService(
        apiConfig: ApiConfig,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): OpenWeatherService {
        return Retrofit.Builder()
            .baseUrl(apiConfig.apiUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(OpenWeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        apiConfig: ApiConfig,
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(apiConfig.readWriteTimeout, TimeUnit.SECONDS)
            .connectTimeout(apiConfig.connectionTimeout, TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor).also {
                if (BuildConfig.DEBUG)
                    it.addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
            }
            .build()
    }
}