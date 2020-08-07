package com.monoprogram.myblend.presentation.top.di

import com.beeete2.android.examples.dagger2.di.AppModule
import com.monoprogram.myblend.MainActivity
import com.monoprogram.myblend.presentation.top.TopFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: TopFragment)
}