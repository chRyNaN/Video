package com.chrynan.video.presentation.reducer

import com.chrynan.inject.Inject
import com.chrynan.video.presentation.core.Reducer
import com.chrynan.video.presentation.state.ChannelDetailsChange
import com.chrynan.video.presentation.state.ChannelDetailsState

class ChannelDetailsReducer @Inject constructor() :
    Reducer<ChannelDetailsState, ChannelDetailsChange> {

    override suspend fun reduce(
        previous: ChannelDetailsState,
        change: ChannelDetailsChange
    ): ChannelDetailsState {
        TODO("Not yet implemented")
    }
}