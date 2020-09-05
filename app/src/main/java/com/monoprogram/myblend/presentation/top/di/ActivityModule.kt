package com.beeete2.android.examples.dagger2.di

import android.content.Context
import com.monoprogram.myblend.TopRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@InstallIn(ActivityComponent::class)
@Module
class ActivityModule {
    @Provides
    fun provideTopRouter(@ActivityContext context: Context): TopRouter {
        return TopRouter(context)
    }
}
