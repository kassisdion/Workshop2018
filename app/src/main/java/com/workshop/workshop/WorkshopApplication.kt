package com.workshop.workshop

import android.app.Application
import android.content.Context
import com.workshop.workshop.di.WorkshopComponent

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
        //Init di HERE
    }
}