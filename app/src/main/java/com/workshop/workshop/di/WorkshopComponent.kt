package com.workshop.workshop.di

import com.workshop.workshop.di.module.AppModule
import com.workshop.workshop.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface WorkshopComponent {
    //Add needed injection target here
}