package com.workshop.workshop.ui

import com.workshop.workshop.ui.base.BaseView

/**
 * Simple Contract between [MainActivity] and [MainPresenter]
 */
interface MainActivityView : BaseView {
    fun showLoading(start: Boolean)
    fun showError(message: String?)
    fun showRefreshing(start: Boolean)
    fun populateObject(data: List<UiObjectModel>)
    fun clearData()
}