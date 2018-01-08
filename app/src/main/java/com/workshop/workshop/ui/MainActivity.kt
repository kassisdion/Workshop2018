package com.workshop.workshop.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.workshop.workshop.R
import com.workshop.workshop.WorkshopApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityView {

    @Inject
    lateinit var mPresenter: MainPresenter

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPresenter()
    }

    /*
    ************************************************************************************************
    ** MainActivityView implementation
    ************************************************************************************************
     */
    override fun showLoading(start: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showRefreshing(start: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun populateObject(data: List<UiObjectModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*
    ************************************************************************************************
    ** Private fun
    ************************************************************************************************
     */
    private fun setupPresenter() {
        WorkshopApplication.workshopComponent.inject(this)
        mPresenter.onAttachView(this)
    }
}
