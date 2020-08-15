package com.chrynan.video.presentation.action.home

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.action.Action
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent

class HomeLoadMoreAction @Inject constructor() : Action<HomeIntent.LoadMore, HomeChange> {

    override suspend fun perform(intent: HomeIntent.LoadMore): HomeChange {
        TODO("Not yet implemented")
    }
}