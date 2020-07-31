package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.model.api.FeedItem
import com.chrynan.common.repository.FeedItemRepository
import com.chrynan.common.utils.filterEachItemIsInstance
import com.chrynan.video.mapper.video.VideoShowcaseMapper
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.viewmodel.AdapterItem
import javax.inject.Inject
import com.chrynan.common.utils.mapEachItemWith
import com.chrynan.video.ui.adapter.factory.calculateAndDispatchDiff
import com.chrynan.common.utils.onError
import com.chrynan.logger.Logger
import com.chrynan.video.ui.adapter.factory.HomeAdapterFactory
import com.chrynan.video.ui.view.HomeView
import com.chrynan.video.ui.view.toggleEmptyState
import kotlinx.coroutines.flow.*

class HomePresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val view: HomeView,
    private val feedRepository: FeedItemRepository,
    private val mapper: VideoShowcaseMapper,
    private val adapterFactory: HomeAdapterFactory
) : BasePresenter(dispatchers) {

    private var isLoading = false
    private var lastCount = 0

    fun loadInitialFeed() = loadFeed()

    fun loadMore() = loadFeed()

    private fun loadFeed() {
        if (!isLoading) {
            feedRepository.getFeedItems()
                .onStart { isLoading = true }
                .onEach { isLoading = false }
                .onError { isLoading = false }
                .flowOn(dispatchers.io)
                .onEach { lastCount = it.size }
                .onEach { view.toggleEmptyState(count = lastCount) }
                .onError { view.toggleEmptyState(count = lastCount) }
                .flowOn(dispatchers.main)
                .filterEachItemIsInstance<FeedItem.VideoFeedItem>()
                .mapEachItemWith(mapper)
                .flowOn(dispatchers.io)
                .calculateAndDispatchDiff(adapterFactory)
                .catch { Logger.logError(message = "Error fetching feed items.", throwable = it) }
                .launchIn(this)
        }
    }
}