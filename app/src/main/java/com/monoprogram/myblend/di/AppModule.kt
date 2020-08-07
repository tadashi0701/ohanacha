package com.beeete2.android.examples.dagger2.di

import com.monoprogram.myblend.TopRouter
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideTopRouter(): TopRouter {
        return TopRouter()
    }
}
