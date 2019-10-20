package com.mikhailbabenkov.wheather.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mikhailbabenkov.wheather.domain.utils.WheatherViewModelFactory
import com.mikhailbabenkov.wheather.ui.main.MainViewModel
import com.mikhailbabenkov.wheather.ui.selectcity.SelectCityViewModel
import com.mikhailbabenkov.wheather.ui.shared.WeatherSharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectCityViewModel::class)
    abstract fun bindSelectCityViewModel(viewModel: SelectCityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherSharedViewModel::class)
    abstract fun bindWeatherSharedViewModel(viewModel: WeatherSharedViewModel): ViewModel

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: WheatherViewModelFactory): ViewModelProvider.Factory
}
