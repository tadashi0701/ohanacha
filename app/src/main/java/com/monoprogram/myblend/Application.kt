package com.monoprogram.myblend

import androidx.room.Room
import com.beeete2.android.examples.dagger2.di.AppModule
import com.monoprogram.myblend.presentation.top.di.AppComponent
import com.monoprogram.myblend.presentation.top.di.DaggerAppComponent

@Suppress("DEPRECATION")
class Application : android.app.Application() {

    companion object {
        lateinit var component: AppComponent private set
        lateinit var instance: Application private set
        lateinit var database: AppDatabase private set
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
        instance = this
        // AppDatabaseをビルドする
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}