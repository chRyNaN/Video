package com.chrynan.video.presentation.action.home

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.action.Action
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent

class HomeLoadInitialAction @Inject constructor() : Action<HomeIntent.LoadInitial, HomeChange> {

    override suspend fun perform(intent: HomeIntent.LoadInitial): HomeChange {
        TODO("Not yet implemented")
    }
}