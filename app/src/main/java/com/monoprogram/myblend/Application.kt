package com.monoprogram.myblend

import android.content.Context
import com.beeete2.android.examples.dagger2.di.AppModule
import com.monoprogram.myblend.di.AppComponent
import com.monoprogram.myblend.di.DaggerAppComponent

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