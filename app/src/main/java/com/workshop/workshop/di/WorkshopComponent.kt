package com.workshop.workshop.di

import com.workshop.workshop.di.module.AppModule
import com.workshop.workshop.di.module.NetworkModule
import com.workshop.workshop.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetworkModule::class)])
interface WorkshopComponent {
    fun inject(mainActivity: MainActivity)
}