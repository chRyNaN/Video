package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ServiceProviderListQualifier
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.viewmodel.AdapterItem
import javax.inject.Inject

class ServiceProviderListPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    @ServiceProviderListQualifier.AdapterItemHandler private val adapterItemHandler: AdapterItemHandler<AdapterItem>
) : BasePresenter(dispatchers) {
}