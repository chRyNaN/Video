package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.model.api.FeedItem
import com.chrynan.common.repository.FeedItemRepository
import com.chrynan.common.utils.filterEachItemIsInstance
import com.chrynan.video.di.qualifier.HomeQualifier
import com.chrynan.video.mapper.video.VideoShowcaseMapper
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.viewmodel.AdapterItem
import javax.inject.Inject
import com.chrynan.common.utils.mapEachItemWith
import com.chrynan.common.utils.onError
import com.chrynan.logger.Logger
import com.chrynan.video.ui.adapter.core.calculateAndDispatchDiff
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class HomePresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val feedRepository: FeedItemRepository,
    private val mapper: VideoShowcaseMapper,
    @HomeQualifier.AdapterItemHandler private val adapterItemHandler: AdapterItemHandler<AdapterItem>
) : BasePresenter(dispatchers) {

    private var isLoading = false

    fun loadInitialFeed() = loadFeed()

    fun loadMore() = loadFeed()

    private fun loadFeed() {
        if (!isLoading) {
            feedRepository.getFeedItems()
                .onStart { isLoading = true }
                .onEach { isLoading = false }
                .onError { isLoading = false }
                .filterEachItemIsInstance<FeedItem.VideoFeedItem>()
                .mapEachItemWith(mapper)
                .calculateAndDispatchDiff(adapterItemHandler)
                .catch { Logger.logError(message = "Error fetching feed items.", throwable = it) }
                .launchIn(this)
        }
    }
}