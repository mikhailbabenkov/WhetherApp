package com.mikhailbabenkov.wheather.di

import com.mikhailbabenkov.wheather.ui.main.MainFragment
import com.mikhailbabenkov.wheather.ui.selectcity.SelectCityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
    @ContributesAndroidInjector
    abstract fun contributeSelectCityFragment(): SelectCityFragment
}
