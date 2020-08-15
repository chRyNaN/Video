package com.chrynan.video.presentation.action.home

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.action.Action
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent

class HomeRefreshAction @Inject constructor() : Action<HomeIntent.Refresh, HomeChange> {

    override suspend fun perform(intent: HomeIntent.Refresh): HomeChange {
        TODO("Not yet implemented")
    }
}