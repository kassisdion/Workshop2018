package com.workshop.workshop.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.workshop.workshop.R
import com.workshop.workshop.WorkshopApplication
import com.workshop.workshop.ui.main.adapter.ObjectAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {
    /*
    ************************************************************************************************
    ** Private field
    ************************************************************************************************
     */
    private var objectAdapter: ObjectAdapter = ObjectAdapter()

    /*
    ************************************************************************************************
    ** Injection
    ************************************************************************************************
     */
    @Inject
    lateinit var presenter: MainActivityPresenter

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

        presenter.onAttachView(this, MainActivityState.getStateFromBundle(savedInstanceState))
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        MainActivityState.writeStateToBundle(outState, presenter.getState())
    }

    /*
    ************************************************************************************************
    ** MainActivityContract implementation
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
        objectAdapter.populateObject(data)
    }

    override fun clearData() {
        objectAdapter.clearData()
    }

    /*
    ************************************************************************************************
    ** Private fun
    ************************************************************************************************
     */
    /**
     * Resolve every [Inject] annotated field
     */
    private fun setupPresenter() {
        WorkshopApplication.workshopComponent.inject(this)
    }

    /**
     * Will setup:
     * -actionBar
     * -swipeRefreshLayout
     * -recycler/adapter
     */
    private fun setupView() {
        //Setup action bar
        setSupportActionBar(mainActivity_toolbar)

        //Setup swipe refresh layout
        mainActivity_swipeRefreshLayout.setOnRefreshListener { presenter.onPullToRefreshActioned() }

        //Setup recycler
        mainActivity_recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = objectAdapter
        }
    }
}
