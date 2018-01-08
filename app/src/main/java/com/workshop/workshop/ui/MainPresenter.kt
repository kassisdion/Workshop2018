package com.workshop.workshop.ui

import com.workshop.workshop.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenter
@Inject
constructor(private val apiService: ApiService) {
    fun testApi() {
        apiService.getObjects()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            //Success, populate view
                        },
                        {
                            //Failure, notify view
                        })
    }
}