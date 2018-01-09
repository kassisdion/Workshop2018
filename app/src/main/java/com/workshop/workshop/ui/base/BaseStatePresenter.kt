package com.workshop.workshop.ui.base

import android.support.annotation.CallSuper

abstract class BaseStatePresenter<V : BaseView, S : BaseState> : BasePresenter<V>() {

    /**
     * Should be called for attaching the presenter with a [BaseView]
     */
    @CallSuper
    open fun onAttachView(view: V, state: S?) {
        super.onAttachView(view)
    }

    abstract fun getState(): S
}