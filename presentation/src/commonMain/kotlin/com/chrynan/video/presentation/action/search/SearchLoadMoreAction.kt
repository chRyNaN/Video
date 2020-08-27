package com.chrynan.video.presentation.action.search

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.action.Action
import com.chrynan.video.presentation.state.SearchChange
import com.chrynan.video.presentation.state.SearchIntent
import kotlinx.coroutines.flow.Flow

class SearchLoadMoreAction @Inject constructor() : Action<SearchIntent.LoadMore, SearchChange> {

    override fun perform(intent: SearchIntent.LoadMore): Flow<SearchChange> {
        TODO("Not yet implemented")
    }
}