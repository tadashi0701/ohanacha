package com.monoprogram.myblend

import com.beeete2.android.examples.dagger2.di.AppModule
import com.monoprogram.myblend.presentation.top.di.AppComponent
import com.monoprogram.myblend.presentation.top.di.DaggerAppComponent

@Suppress("DEPRECATION")
class Application : android.app.Application() {

    companion object {
        lateinit var component: AppComponent private set
        lateinit var instance: Application private set
    }

    override fun onCreate() {
        super.onCreate()
        // ↓↓これ
        component = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
        // ↑↑これ
        instance = this
    }
}