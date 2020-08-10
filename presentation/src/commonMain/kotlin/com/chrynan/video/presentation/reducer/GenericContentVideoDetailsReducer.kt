package com.chrynan.video.presentation.reducer

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.state.GenericContentVideoDetailsChange
import com.chrynan.video.presentation.state.GenericContentVideoDetailsState

class GenericContentVideoDetailsReducer @Inject constructor() :
    Reducer<GenericContentVideoDetailsState, GenericContentVideoDetailsChange> {

    override suspend fun reduce(
        previous: GenericContentVideoDetailsState,
        change: GenericContentVideoDetailsChange
    ): GenericContentVideoDetailsState {
        TODO("Not yet implemented")
    }
}