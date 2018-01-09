package com.workshop.workshop.ui.main

import android.os.Bundle

/**
 * Simple class used to represent [MainActivity] state
 * Contains deps with android framework, they could be move into [MainActivity] for unit testing purpose
 */
class MainActivityState(private val data: List<UiObjectModel>?) : MainActivityContract.State {
    override fun getLastItems(): List<UiObjectModel>? {
        return data
    }

    companion object {
        private val STATE_KEY_ITEMS = "MainActivity.STATE_KEY_ITEMS"

        /**
         * Will return null if the given state is null or if doesn't contains ALL states
         */
        fun getStateFromBundle(savedInstanceState: Bundle?): MainActivityContract.State? {
            return when {
                savedInstanceState == null -> null
                !savedInstanceState.containsKey(STATE_KEY_ITEMS) -> null
                else -> {
                    val items: ArrayList<UiObjectModel>? = savedInstanceState.getParcelableArrayList(STATE_KEY_ITEMS)

                    MainActivityState(items)
                }
            }
        }

        fun writeStateToBundle(outState: Bundle?,
                               state: MainActivityContract.State) {
            outState?.apply {
                state.getLastItems()?.let {
                    outState.putParcelableArrayList(STATE_KEY_ITEMS, ArrayList(it))
                }
            }
        }
    }
}