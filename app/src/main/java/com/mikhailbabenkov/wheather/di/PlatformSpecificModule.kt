package com.mikhailbabenkov.wheather.di

import android.content.Context
import com.mikhailbabenkov.wheather.data.api.StorageService
import com.mikhailbabenkov.wheather.data.api.StorageServiceImpl
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
    fun provideApiConfig(): ApiConfig {
        return ApiConfigImpl()
    }

    @Singleton
    @Provides
    fun provideStorageService(context: Context): StorageService {
        return StorageServiceImpl(context)
    }

    @Provides
    fun provideCoroutinesDispatcherProvider() = CoroutinesDispatcherProvider(
        Dispatchers.Main,
        Dispatchers.Default,
        Dispatchers.IO
    )
}