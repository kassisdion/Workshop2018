package com.workshop.workshop.ui.main

import com.workshop.workshop.api.ApiService
import com.workshop.workshop.api.response.ObjectModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityPresenter
@Inject
constructor(private val apiService: ApiService) : MainActivityContract.Presenter() {
    /*
    ************************************************************************************************
    ** Private field
    ************************************************************************************************
     */
    private var lastRetrievedData: List<UiObjectModel>? = null

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    /**
     * Will check state:
     * if none: see [retrieveData]
     * if any: will store the previous state and populate the view with it
     */
    override fun onAttachView(view: MainActivityContract.View,
                              state: MainActivityContract.State?) {
        super.onAttachView(view, state)

        if (state == null) {
            retrieveData(false)
        } else {
            state.getLastItems()?.let {
                lastRetrievedData = it
                view.populateObject(it)
            }
        }
    }

    override fun getState(): MainActivityContract.State {
        return MainActivityState(lastRetrievedData)
    }

    /*
    ************************************************************************************************
    ** MainActivityContract.Presenter() implementation
    ************************************************************************************************
     */
    override fun onPullToRefreshActioned() = retrieveData(true)

    /*
    ************************************************************************************************
    ** Private method
    ************************************************************************************************
     */
    /**
     * Make a request with [ApiService.getObjects]
     * if success:
     * -Populate the view with [MainActivityContract.View.populateObject]
     *
     * if faillure
     * -Call [MainActivityContract.View.clearData]
     *
     * @param isRefreshing is from a pullToRefresh
     *
     */
    private fun retrieveData(isRefreshing: Boolean) {
        showLoadingAnimation(isRefreshing, true)

        disposables.add(apiService.getObjects()
                .subscribeOn(Schedulers.io())
                .map { rawObjectToUiObject(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate({ showLoadingAnimation(isRefreshing, false) })
                .subscribe(
                        { data ->
                            lastRetrievedData = data

                            view?.apply {
                                if (isRefreshing) {
                                    clearData()
                                }
                                populateObject(data)
                            }
                        },
                        {
                            view?.showError(it.message)
                        })
        )
    }

    /**
     * Convert a given list of [ObjectModel] into a list of [UiObjectModel]
     */
    private fun rawObjectToUiObject(rawObject: List<ObjectModel>) = rawObject
            .sortedBy { it.title }
            .map {
                UiObjectModel(it.albumId, it.id, it.title, it.url, it.thumbnailUrl)
            }

    /**
     *
     */
    private fun showLoadingAnimation(isRefreshing: Boolean,
                                     start: Boolean) {
        view?.apply {
            when (isRefreshing) {
                true -> showRefreshing(start)
                false -> showLoading(start)
            }
        }
    }
}