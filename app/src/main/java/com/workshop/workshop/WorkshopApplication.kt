package com.workshop.workshop

import android.app.Application
import android.content.Context
import com.workshop.workshop.di.DaggerWorkshopComponent
import com.workshop.workshop.di.WorkshopComponent
import com.workshop.workshop.di.module.AppModule
import com.workshop.workshop.di.module.NetworkModule

class WorkshopApplication : Application() {

    /*
    ************************************************************************************************
    ** Singleton
    ************************************************************************************************
    */
    /**
     * Simple singleton used to access to [WorkshopComponent] instance needed for resolving injection
     */
    companion object {
        lateinit var workshopComponent: WorkshopComponent

        operator fun get(context: Context): WorkshopApplication {
            return context.applicationContext as WorkshopApplication
        }
    }

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
    */
    override fun onCreate() {
        super.onCreate()
        setupComponents()
    }

    /*
    ************************************************************************************************
    ** Private fun
    ************************************************************************************************
    */
    private fun setupComponents() {
        workshopComponent = DaggerWorkshopComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule())
                .build()
    }
}