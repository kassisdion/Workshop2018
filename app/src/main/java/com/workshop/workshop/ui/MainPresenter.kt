package com.workshop.workshop.ui

import com.workshop.workshop.api.ApiService
import com.workshop.workshop.api.response.ObjectModel
import com.workshop.workshop.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter
@Inject
constructor(private val apiService: ApiService) : BasePresenter<MainActivityView>() {

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    override fun onAttachView(view: MainActivityView) {
        super.onAttachView(view)
        retrieveData(false)
    }

    /*
    ************************************************************************************************
    ** Public method
    ************************************************************************************************
     */
    fun onPullToRefreshActioned() = retrieveData(true)

    /*
    ************************************************************************************************
    ** Private method
    ************************************************************************************************
     */
    private fun retrieveData(isRefreshing: Boolean) {
        showLoadingAnimation(isRefreshing, true)

        disposables.add(apiService.getObjects()
                .subscribeOn(Schedulers.io())
                .map { rawObjectToUiObject(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate({ showLoadingAnimation(isRefreshing, false) })
                .subscribe(
                        {
                            view?.apply {
                                clearData()
                                populateObject(it)
                            }
                        },
                        {
                            view?.showError(it.message)
                        })
        )
    }

    private fun rawObjectToUiObject(rawObject: List<ObjectModel>) = rawObject.map {
        UiObjectModel(it.albumId, it.id, it.title, it.url, it.thumbnailUrl)
    }

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