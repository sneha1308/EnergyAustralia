package com.android.musicfestival.viewmodel

import androidx.lifecycle.ViewModel
import com.android.musicfestival.network.BaseView
import java.lang.ref.WeakReference


abstract class BaseViewModel<N : BaseView?> : ViewModel() {
    private var mNavigator: WeakReference<N>? = null
    val navigator: N?
        get() = if (mNavigator != null) {
            mNavigator!!.get()
        } else null

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

    override fun onCleared() {
        super.onCleared()
        if (mNavigator != null) {
            mNavigator = null
        }
    }
}