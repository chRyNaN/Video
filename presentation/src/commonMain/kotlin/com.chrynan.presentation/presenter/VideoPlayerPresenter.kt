package com.chrynan.presentation.presenter

import com.chrynan.common.Inject
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.presentation.adapter.core.AdapterItemHandler
import com.chrynan.presentation.viewmodel.AdapterItem

class VideoPlayerPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val adapterHandler: AdapterItemHandler<AdapterItem>
) : BasePresenter(dispatchers) {

    fun loadMore() {

    }
}