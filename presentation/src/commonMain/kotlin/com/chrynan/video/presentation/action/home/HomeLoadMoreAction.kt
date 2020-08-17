package com.chrynan.video.presentation.action.home

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.action.Action
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeIntent
import com.chrynan.video.presentation.viewmodel.AdapterItem
import kotlinx.coroutines.flow.Flow

class HomeLoadMoreAction @Inject constructor() : Action<HomeIntent.LoadMore, HomeChange> {

    operator fun invoke(
        intent: HomeIntent.LoadMore,
        currentItems: List<AdapterItem>
    ): Flow<HomeChange> {
        TODO("Not yet implemented")
    }
}