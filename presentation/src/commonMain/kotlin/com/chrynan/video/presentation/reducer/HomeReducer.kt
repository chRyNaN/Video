package com.chrynan.video.presentation.reducer

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeState

class HomeReducer @Inject constructor(): Reducer<HomeState, HomeChange> {

    override suspend fun reduce(previous: HomeState, change: HomeChange): HomeState {
        TODO("Not yet implemented")
    }
}