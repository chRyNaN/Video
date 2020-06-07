package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.repository.ServiceProviderRepository
import com.chrynan.common.utils.mapEachItemWith
import com.chrynan.logger.Logger
import com.chrynan.video.di.qualifier.ServiceProviderListQualifier
import com.chrynan.video.mapper.provider.ServiceProviderMapper
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import com.chrynan.video.ui.view.ServiceProviderListView
import com.chrynan.video.viewmodel.AdapterItem
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ServiceProviderListPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val view: ServiceProviderListView,
    private val serviceProviderRepository: ServiceProviderRepository,
    private val mapper: ServiceProviderMapper,
    @ServiceProviderListQualifier.AdapterItemHandler private val adapterItemHandler: AdapterItemHandler<AdapterItem>
) : BasePresenter(dispatchers) {

    fun loadItems() {
        serviceProviderRepository.getAll()
            .mapEachItemWith(mapper)
            .calculateAndDispatchDiff(adapterItemHandler)
            .flowOn(dispatchers.io)
            .onEach { if (it.items.isEmpty()) view.showEmptyState() else view.showListState() }
            .flowOn(dispatchers.main)
            .catch { Logger.logError(throwable = it, message = "Error Loading Services.") }
            .launchIn(this)
    }
}