package com.workshop.workshop.di.module

import android.app.Application
import android.content.Context
import com.workshop.workshop.WorkshopApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: WorkshopApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): Application = app
}