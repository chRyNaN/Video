package com.chrynan.video.presentation.action.search

import com.chrynan.inject.Inject
import com.chrynan.video.presentation.core.Action
import com.chrynan.video.presentation.state.SearchChange
import com.chrynan.video.presentation.state.SearchIntent
import kotlinx.coroutines.flow.Flow

class SearchQueryAction @Inject constructor() : Action<SearchIntent.Search, SearchChange> {

    override fun perform(intent: SearchIntent.Search): Flow<SearchChange> {
        TODO("Not yet implemented")
    }
}