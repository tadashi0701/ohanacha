package com.monoprogram.myblend

import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
@Suppress("DEPRECATION")
class Application : android.app.Application() {

    companion object {
        lateinit var instance: Application private set
        lateinit var database: AppDatabase private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // AppDatabaseをビルドする
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}