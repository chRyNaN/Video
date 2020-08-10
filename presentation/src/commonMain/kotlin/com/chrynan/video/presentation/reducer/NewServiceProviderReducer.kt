package com.chrynan.video.presentation.reducer

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.state.NewServiceProviderChange
import com.chrynan.video.presentation.state.NewServiceProviderState

class NewServiceProviderReducer @Inject constructor() :
    Reducer<NewServiceProviderState, NewServiceProviderChange> {

    override suspend fun reduce(
        previous: NewServiceProviderState,
        change: NewServiceProviderChange
    ): NewServiceProviderState {
        TODO("Not yet implemented")
    }
}