package com.workshop.workshop.ui.base

import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter<T : BaseView> {
    /*
    ************************************************************************************************
    ** Protected field
    ************************************************************************************************
    */
    /**
     * disposables container
     */
    protected val disposables = CompositeDisposable()

    /**
     * View linked with the presenter (or null if the presenter is detached)
     */
    protected var view: T? = null
        private set

    /*
    ************************************************************************************************
    ** Life cycle
    ************************************************************************************************
     */
    /**
     * Should be called for attaching the presenter with a [BaseView]
     */
    @CallSuper
    open fun onAttachView(view: T) {
        this.view = view
    }

    /**
     * Should be called for detaching the presenter from a [BaseView]
     * Will clear [disposables]
     */
    @CallSuper
    open fun onDetachView() {
        view = null
        disposables.clear()
    }
}