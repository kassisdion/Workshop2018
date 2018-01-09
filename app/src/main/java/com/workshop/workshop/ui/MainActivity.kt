package com.workshop.workshop.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.workshop.workshop.R
import com.workshop.workshop.WorkshopApplication
import kotlinx.android.synthetic.main.activity_main.*
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

        setupView()
        setupPresenter()
    }

    /*
    ************************************************************************************************
    ** MainActivityView implementation
    ************************************************************************************************
     */
    override fun showLoading(start: Boolean) {
        mainActivity_progressBar.visibility = if (start) View.VISIBLE else View.GONE
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showRefreshing(start: Boolean) {
        mainActivity_swipeRefreshLayout.isRefreshing = start
    }

    override fun populateObject(data: List<UiObjectModel>) {
        //Call adapter.addAll(data) here
    }

    override fun clearData() {
        //Call adapter.clear here
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

    fun setupView() {
        setSupportActionBar(mainActivity_toolbar)

        mainActivity_swipeRefreshLayout.setOnRefreshListener { mPresenter.onPullToRefreshActionned() }
    }
}
