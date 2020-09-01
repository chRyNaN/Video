package com.chrynan.video.presentation.action.home

import com.chrynan.inject.Inject
import com.chrynan.video.common.model.api.FeedItem
import com.chrynan.video.common.repository.FeedItemRepository
import com.chrynan.video.common.utils.startWith
import com.chrynan.video.presentation.core.Action
import com.chrynan.video.presentation.mapper.video.VideoShowcaseMapper
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class HomeLoadInitialAction @Inject constructor(
    private val feedItemRepository: FeedItemRepository,
    private val mapper: VideoShowcaseMapper
) : Action<HomeIntent.LoadInitial, HomeChange> {

    override fun perform(intent: HomeIntent.LoadInitial): Flow<HomeChange> =
        feedItemRepository.get()
            .filterNotNull()
            .map { items ->
                items.filterIsInstance<FeedItem<FeedQuery.AsVideoFeedItem>>()
                    .map { mapper.map(it) }
            }
            .map { HomeChange.Loaded(items = it) }
            .startWith(HomeChange.StartedLoading())
}