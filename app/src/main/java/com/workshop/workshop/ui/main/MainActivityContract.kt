package com.workshop.workshop.ui.main

import com.workshop.workshop.ui.base.BaseState
import com.workshop.workshop.ui.base.BaseStatePresenter
import com.workshop.workshop.ui.base.BaseView

/**
 * Simple Contract between [MainActivity] and [MainActivityPresenter]
 */
interface MainActivityContract {

    interface View : BaseView {
        fun showLoading(start: Boolean)
        fun showError(message: String?)
        fun showRefreshing(start: Boolean)
        fun populateObject(data: List<UiObjectModel>)
        fun clearData()
    }

    interface State : BaseState {
        fun getLastItems(): List<UiObjectModel>?
    }

    abstract class Presenter : BaseStatePresenter<MainActivityContract.View, MainActivityContract.State>() {
        abstract fun onPullToRefreshActioned()
    }

}