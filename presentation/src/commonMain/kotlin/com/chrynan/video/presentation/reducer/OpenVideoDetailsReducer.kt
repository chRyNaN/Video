package com.chrynan.video.presentation.reducer

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.state.OpenVideoDetailsChange
import com.chrynan.video.presentation.state.OpenVideoDetailsState

class OpenVideoDetailsReducer @Inject constructor(): Reducer<OpenVideoDetailsState, OpenVideoDetailsChange> {

    override suspend fun reduce(
        previous: OpenVideoDetailsState,
        change: OpenVideoDetailsChange
    ): OpenVideoDetailsState {
        TODO("Not yet implemented")
    }
}