package com.chrynan.video.presentation.action.home

import com.chrynan.inject.Inject
import com.chrynan.video.common.model.api.FeedItem
import com.chrynan.video.common.repository.FeedItemRepository
import com.chrynan.video.common.utils.startWith
import com.chrynan.video.presentation.core.Action
import com.chrynan.video.presentation.mapper.video.VideoShowcaseMapper
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@OptIn(ExperimentalCoroutinesApi::class)
class HomeLoadMoreAction @Inject constructor(
    private val feedItemRepository: FeedItemRepository,
    private val mapper: VideoShowcaseMapper
) : Action<HomeIntent.LoadMore, HomeChange> {

    override fun perform(intent: HomeIntent.LoadMore): Flow<HomeChange> =
        feedItemRepository.get()
            .onStart { feedItemRepository.loadMore() }
            .filterNotNull()
            .map { items ->
                items.filterIsInstance<FeedItem<FeedQuery.AsVideoFeedItem>>()
                    .map { mapper.map(it) }
            }
            .map { HomeChange.Loaded(items = it) }
            .startWith(HomeChange.StartedLoading(currentItems = intent.currentItems))
}