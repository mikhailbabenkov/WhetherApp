package com.mikhailbabenkov.wheather.di

import android.content.Context
import com.mikhailbabenkov.wheather.WheatherApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        FragmentBuildersModule::class,
        PlatformSpecificModule::class]
)
interface AppComponent : AndroidInjector<WheatherApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}
