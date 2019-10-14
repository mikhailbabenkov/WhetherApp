package com.mikhailbabenkov.wheather.di

import android.app.Application
import android.content.Context
import com.mikhailbabenkov.wheather.domain.utils.ApiConfig
import com.mikhailbabenkov.wheather.domain.utils.ApiConfigImpl
import com.mikhailbabenkov.wheather.domain.utils.CoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [AppModule::class, ViewModelModule::class, UseCaseModule::class])
class PlatformSpecificModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideApiConfig(): ApiConfig {
        return ApiConfigImpl()
    }

    @Provides
    fun provideCoroutinesDispatcherProvider() = CoroutinesDispatcherProvider(
        Dispatchers.Main,
        Dispatchers.Default,
        Dispatchers.IO
    )
}