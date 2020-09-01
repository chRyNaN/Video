package com.chrynan.video.presentation.action.search

import com.chrynan.inject.Inject
import com.chrynan.video.presentation.core.Action
import com.chrynan.video.presentation.state.SearchChange
import com.chrynan.video.presentation.state.SearchIntent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SearchClearAction @Inject constructor() : Action<SearchIntent.Clear, SearchChange> {

    override fun perform(intent: SearchIntent.Clear): Flow<SearchChange> =
        flowOf(SearchChange.Cleared)
}