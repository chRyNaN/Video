package com.chrynan.video.presentation.reducer

import com.chrynan.common.Inject
import com.chrynan.video.presentation.state.LbryVideoDetailsChange
import com.chrynan.video.presentation.state.LbryVideoDetailsState

class LbryVideoDetailsReducer @Inject constructor() :
    Reducer<LbryVideoDetailsState, LbryVideoDetailsChange> {

    override suspend fun reduce(
        previous: LbryVideoDetailsState,
        change: LbryVideoDetailsChange
    ): LbryVideoDetailsState {
        TODO("Not yet implemented")
    }
}