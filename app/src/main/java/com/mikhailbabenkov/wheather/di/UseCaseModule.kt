package com.mikhailbabenkov.wheather.di

import com.mikhailbabenkov.wheather.data.repository.WeatherRepository
import com.mikhailbabenkov.wheather.domain.usecase.GetCitiesUseCase
import com.mikhailbabenkov.wheather.domain.usecase.GetForecastUseCase
import com.mikhailbabenkov.wheather.domain.usecase.SetCurrentCityUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetCitiesUseCase(repository: WeatherRepository): GetCitiesUseCase {
        return GetCitiesUseCase(repository)
    }
    @Provides
    fun provideGetForecastUseCase(repository: WeatherRepository): GetForecastUseCase {
        return GetForecastUseCase(repository)
    }
    @Provides
    fun provideSetCurrectCityUseCase(repository: WeatherRepository): SetCurrentCityUseCase {
        return SetCurrentCityUseCase(repository)
    }
}