package com.chrynan.video.presentation.action.home

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.action.Action
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent
import kotlinx.coroutines.flow.Flow

class HomeLoadInitialAction @Inject constructor() : Action<HomeIntent.LoadInitial, HomeChange> {

    operator fun invoke(intent: HomeIntent.LoadInitial): Flow<HomeChange> {
        TODO("Not yet implemented")
    }
}