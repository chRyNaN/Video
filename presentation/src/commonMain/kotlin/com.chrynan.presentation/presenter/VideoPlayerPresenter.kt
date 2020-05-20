package com.chrynan.presentation.presenter

import com.chrynan.common.Inject
import com.chrynan.common.coroutine.CoroutineDispatchers

class VideoPlayerPresenter @Inject constructor(dispatchers: CoroutineDispatchers) :
    BasePresenter(dispatchers) {

    override fun onBind() {
    }

    override fun onUnbind() {
    }
}