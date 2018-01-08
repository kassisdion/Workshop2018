package com.workshop.workshop.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.workshop.workshop.R
import com.workshop.workshop.WorkshopApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WorkshopApplication.workshopComponent.inject(this)
        mPresenter.testApi()
    }
}
